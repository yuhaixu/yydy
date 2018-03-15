package com.freesheep.web.controller.app;

import com.freesheep.biz.WxPayBean.WxNotifyBean;
import com.freesheep.biz.WxPayBean.WxReturnBean;
import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.biz.service.ProductCouponService;
import com.freesheep.biz.service.StClaimOrderService;
import com.freesheep.biz.service.StOrdersService;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.JiuUtils;
import com.freesheep.web.util.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/app/wx_pay")
public class WxPayController extends BaseController {

    private Logger log = Logger.getLogger(WxPayController.class);

    @Resource
    StClaimOrderService claimOrderService;
    @Resource
    StOrdersService ordersService;
    @Resource
    ProductCouponService couponService;


    @ResponseBody
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "application/xml")
    public String wxNotify(){
        String body = getBodyInfo();
        log.debug("=================wx pay notify===================");
        log.debug(body);

        XStream notifyStream = JiuUtils.createXstream();
        notifyStream.alias("xml", WxNotifyBean.class);
        notifyStream.processAnnotations(WxNotifyBean.class);
        WxNotifyBean wxNotifyBean = (WxNotifyBean) notifyStream.fromXML(body);
        String returnCode = wxNotifyBean.getReturnCode();

        WxReturnBean wxReturnBean = new WxReturnBean();
        if("SUCCESS".equals(returnCode)){
            wxReturnBean.setReturnCode("SUCCESS");
            String orderNum = wxNotifyBean.getOutTradeNo();
            String transactionId = wxNotifyBean.getTransactionId();
            if(StringUtils.isNotBlank(orderNum)) payOrder(orderNum);
        } else {
            wxReturnBean.setReturnCode("FAIL");
            wxReturnBean.setReturnMsg("支付失败");
        }


        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.processAnnotations(wxReturnBean.getClass());
        String xml = xStream.toXML(wxReturnBean);
        xml = xml.replaceAll("__", "_");
        return xml;
    }

    private void payOrder(String orderNum){
        if(StringUtils.isBlank(orderNum)) return;
        Date date = new Date();
        if(orderNum.contains("ip")){
            // ios 购买商品支付
            operateOrder(orderNum, "ip");
        } else if(orderNum.contains("ap")){
            // android 购买商品支付
            operateOrder(orderNum, "ap");
        } else {
            int id = Utils.parseInt(orderNum);
            if(id <= 0) return;
            StOrdersBO sBo = ordersService.selectByPrimaryKey(id);
            if(sBo == null) return;
            if(sBo.getPayStatus() == 0 && sBo.getPayType() == 0 && sBo.getOrderStatus() == 0) {
                StOrdersBO bo = new StOrdersBO();
                bo.setModified(date);
                bo.setId(id);
                bo.setPayStatus(1);
                bo.setPayType(2);
                bo.setOrderStatus(1);
                ordersService.updateByPrimaryKeySelective(bo);
            }
        }
    }

    private void operateOrder(String orderNum,String regex){
        String[] arr = orderNum.split(regex);
        if(arr == null || arr.length != 2) return;
        int id = Utils.parseInt(arr[1]);
        StOrdersBO sBo = ordersService.selectByPrimaryKey(id);
        if(sBo == null) return;
        Date date = new Date();
        if(sBo.getPayStatus() == 0 && sBo.getPayType() == 0 && sBo.getOrderStatus() == 0) {
            StOrdersBO bo = new StOrdersBO();
            bo.setModified(date);
            bo.setId(id);
            bo.setPayStatus(1);
            bo.setPayType(2);
            bo.setOrderStatus(1);
            ordersService.updateByPrimaryKeySelective(bo);
        }
        Long cId = Utils.parseLong(arr[0]);
        if(cId > 0) {
            ProductCouponBO couBo = couponService.selectByPrimaryKey(cId);
            if (couBo == null) return;
            ProductCouponBO upBo = new ProductCouponBO();
            upBo.setModifyTime(date);
            upBo.setIsUsed(1);
            upBo.setId(cId);
            couponService.updateByPrimaryKeySelective(couBo);
        }
    }

}
