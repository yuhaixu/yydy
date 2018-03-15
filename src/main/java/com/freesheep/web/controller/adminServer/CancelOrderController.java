package com.freesheep.web.controller.adminServer;

import com.freesheep.biz.jiuBean.OrderCancelBean;
import com.freesheep.biz.jiuBean.ResponseBean;
import com.freesheep.biz.model.OrderCancelBO;
import com.freesheep.biz.service.OrderCancelService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.JiuUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
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
import java.util.Map;

@Controller
@RequestMapping("/admin/order_cancel")
public class CancelOrderController extends BaseSecretController {

    private Logger log = Logger.getLogger(CancelOrderController.class);
    @Resource
    private OrderCancelService orderCancelService;


    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResultView cancel(){
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = testBody();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }

        if (parMap == null) return result(null, "参数错误");
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        String orderCode = Utils.getSomeValue(parMap.get("order_code"));
        String orderType = Utils.getSomeValue(parMap.get("order_type"));
        String cancelReason = Utils.getSomeValue(parMap.get("cancel_reason"));

        if (StringUtils.isBlank(warehouseCode) || StringUtils.isBlank(orderCode) || StringUtils.isBlank(orderType)){
            result(null, "参数错误");
        }

        OrderCancelBean orderCancelBean = new OrderCancelBean();
        orderCancelBean.setWarehouseCode(warehouseCode);
        orderCancelBean.setOwnerCode(JiuConfig.OWNER_CODE);
        orderCancelBean.setOrderCode(orderCode);
        orderCancelBean.setOrderType(orderType);
        if(StringUtils.isNotBlank(cancelReason)) orderCancelBean.setCancelReason(cancelReason);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", OrderCancelBean.class);
        String xml = xStream.toXML(orderCancelBean);

        String url = JiuUtils.getRequestUrl("order.cancel", xml);
        if (StringUtils.isBlank(url)) return result(null, "单据取消失败");

        log.debug("===================return order request url===========================");
        log.debug(url);
        log.debug("====================return order request url==========================");

        log.debug("===================return order body===========================");
        log.debug(xml);
        log.debug("====================return order body==========================");

        String jiuResp = new HttpRequest().xmlPost(url, xml);
        log.debug("===================return order response===========================");
        log.debug(jiuResp);
        log.debug("====================return order response==========================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);
        if (responseBean != null) {
            if ("100000".equals(responseBean.getCode())) {
                Date date = new Date();
                OrderCancelBO orderCancelBO = new OrderCancelBO();
                orderCancelBO.setWarehouseCode(warehouseCode);
                orderCancelBO.setOwnerCode(JiuConfig.OWNER_CODE);
                orderCancelBO.setOrderCode(orderCode);
                orderCancelBO.setOrderType(orderType);
                if(StringUtils.isNotBlank(cancelReason)) orderCancelBO.setCancelReason(cancelReason);
                orderCancelBO.setCreateTime(date);
                orderCancelBO.setModifyTime(date);
                orderCancelService.insertSelective(orderCancelBO);
                return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
            }
        }
        return result(null, "单据取消失败");
    }

}
