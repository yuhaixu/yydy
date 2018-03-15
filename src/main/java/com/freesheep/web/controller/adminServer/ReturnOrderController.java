package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.jiuBean.*;
import com.freesheep.biz.model.ReturnOrderBO;
import com.freesheep.biz.model.ReturnOrderInfoBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.ReturnOrderInfoService;
import com.freesheep.biz.service.ReturnOrderService;
import com.freesheep.biz.service.StProductsService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.JiuUtils;
import com.freesheep.web.util.OrderUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/return_order")
public class ReturnOrderController extends BaseSecretController {

    private Logger log = Logger.getLogger(ReturnOrderController.class);

    @Resource
    StProductsService productsService;
    @Resource
    ReturnOrderService returnOrderService;
    @Resource
    ReturnOrderInfoService returnOrderInfoService;


    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST, produces = "application/xml")
    public Object Confirm(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        log.debug("confirm");
        String body = getBodyInfo();
        log.debug("============StockoutOrderController confirm body start================");
        log.debug(body);
        log.debug("========StockoutOrderController confirm body end============");

        XStream xStream = JiuUtils.createXstream();
        xStream.alias("request", ReturnOrderCreateBean.class);
        xStream.alias("returnOrder", ReturnOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);
        xStream.processAnnotations(ReturnOrderCreateBean.class);
        ReturnOrderCreateBean returnOrderCreateBean = (ReturnOrderCreateBean) xStream.fromXML(body);
        if (returnOrderCreateBean != null) {
            ReturnOrderBean returnOrderBean = returnOrderCreateBean.getReturnOrder();
            if(returnOrderBean != null){
                String returnOrderCode = returnOrderBean.getReturnOrderCode();
                String returnOrderId = returnOrderBean.getReturnOrderId();
                String orderConfirmTime = returnOrderBean.getOrderConfirmTime();
                String outBizCode = returnOrderBean.getOutBizCode();
                Date date = new Date();

                List<OrderLineItemBean> orderLineList = returnOrderCreateBean.getOrderLines();

                for (int i = 0; i < orderLineList.size(); i++) {
                    OrderLineItemBean orderLineItemBean = orderLineList.get(i);
                    String itemCode = orderLineItemBean.getItemCode();
                    // TODO test item code 69180125
                    ReturnOrderInfoBO returnOrderInfoBO = returnOrderInfoService.selectReturnOrderByNum(returnOrderCode, returnOrderId, "69180125");
                    if(returnOrderInfoBO == null){
                        log.debug("===========数据存储错误=============");
                        return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "406", "数据存储错误"));
                    }
                    returnOrderInfoBO.setOrderConfirmTime(orderConfirmTime);
                    returnOrderInfoBO.setOutBizCode(outBizCode);
                    returnOrderInfoBO.setInventoryType(orderLineItemBean.getInventoryType());
                    returnOrderInfoBO.setActualQty(orderLineItemBean.getActualQty());
                    returnOrderInfoBO.setModifyTime(date);
                    int upLow = returnOrderInfoService.updateByPrimaryKeySelective(returnOrderInfoBO);
                    if(upLow < 1){
                        log.debug("===========写入数据失败=============");
                        return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "407", "写入数据失败"));
                    }
                }

            } else {
                log.debug("===========接收数据有问题=============");
                return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "401", "接收数据有问题"));
            }

        } else {
            log.debug("===========未接收到返回数据=============");
            return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "400", "未接收到返回数据"));
        }


        log.debug("===========StockoutOrderController confirm  success=============");
        String returnXml = JiuUtils.getResponseXml(JiuUtils.getBean("success", "200", null));
        log.debug("==============StockoutOrderController confirm  success return xml====================");
        log.debug(returnXml);
        log.debug("==============StockoutOrderController confirm success return xml====================");
        return returnXml;
    }


    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultView create(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
//            parMap = getBodyMap();
        } catch (Exception e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        if (parMap == null) return result(null, "参数错误");
        String productStr = Utils.getSomeValue(parMap.get("pid_arr"));
        if (StringUtils.isBlank(productStr)) return result(null, "参数错误");

        List<CreateOutOrderProBean> list = JSONObject.parseObject(productStr, new TypeReference<List<CreateOutOrderProBean>>() {
        });
        if (list == null || list.size() <= 0) return result(null, "参数错误");

        // 仓库编码
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        // 业务类型
        String orderType = Utils.getSomeValue(parMap.get("order_type"));
        // 原出库单号
        String preDeliveryOrderCode = Utils.getSomeValue(parMap.get("pre_delivery_order_code"));
        String logisticsCode = Utils.getSomeValue(parMap.get("logistics_code"));
        String expressCode = Utils.getSomeValue(parMap.get("express_code"));
        String returnReason = Utils.getSomeValue(parMap.get("return_reason"));
        String buyerNick = Utils.getSomeValue(parMap.get("buyer_nick"));
        String sendCompany = Utils.getSomeValue(parMap.get("send_company"));
        String sendName = Utils.getSomeValue(parMap.get("send_name"));
        String sendTel = Utils.getSomeValue(parMap.get("send_tel"));
        String sendMobile = Utils.getSomeValue(parMap.get("send_mobile"));
        String sendEmail = Utils.getSomeValue(parMap.get("send_email"));
        String sendProvince = Utils.getSomeValue(parMap.get("send_province"));
        String sendCity = Utils.getSomeValue(parMap.get("send_city"));
        String sendArea = Utils.getSomeValue(parMap.get("send_area"));
        String sendDetailAddress = Utils.getSomeValue(parMap.get("send_detail_address"));
        String remark = Utils.getSomeValue(parMap.get("remark"));

        if (StringUtils.isBlank(warehouseCode) || StringUtils.isBlank(orderType) || StringUtils.isBlank(preDeliveryOrderCode)
                || StringUtils.isBlank(sendName) || StringUtils.isBlank(sendMobile) || StringUtils.isBlank(sendProvince)
                || StringUtils.isBlank(sendCity) || StringUtils.isBlank(sendArea) || StringUtils.isBlank(sendDetailAddress) ){
            result(null, "参数错误");
        }


        // 出库单创建
        ReturnOrderCreateBean returnOrderCreateBean = new ReturnOrderCreateBean();
        String orderNum = "TH" + OrderUtils.getOrder();
        ReturnOrderBean returnOrderBean = new ReturnOrderBean();
        returnOrderBean.setReturnOrderCode(orderNum);
        returnOrderBean.setWarehouseCode(warehouseCode);
        returnOrderBean.setOrderType(orderType);
        returnOrderBean.setPreDeliveryOrderCode(preDeliveryOrderCode);
        if (StringUtils.isNotBlank(logisticsCode)) returnOrderBean.setLogisticsCode(logisticsCode);
        if (StringUtils.isNotBlank(expressCode)) returnOrderBean.setExpressCode(expressCode);
        if (StringUtils.isNotBlank(returnReason)) returnOrderBean.setReturnReason(returnReason);
        if (StringUtils.isNotBlank(buyerNick)) returnOrderBean.setBuyerNick(buyerNick);
        if (StringUtils.isNotBlank(remark)) returnOrderBean.setRemark(remark);

        SenderInfoBean senderInfoBean = new SenderInfoBean();
        if (StringUtils.isNotBlank(sendCompany)) senderInfoBean.setCompany(sendCompany);
        senderInfoBean.setName(sendName);
        if (StringUtils.isNotBlank(sendTel)) senderInfoBean.setTel(sendTel);
        senderInfoBean.setMobile(sendMobile);
        if (StringUtils.isNotBlank(sendEmail)) senderInfoBean.setEmail(sendEmail);
        senderInfoBean.setProvince(sendProvince);
        senderInfoBean.setCity(sendCity);
        senderInfoBean.setArea(sendArea);
        senderInfoBean.setDetailAddress(sendDetailAddress);
        returnOrderBean.setSenderInfo(senderInfoBean);

        List<OrderLineItemBean> orderLines = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CreateOutOrderProBean createOutOrderProBean = list.get(i);
            String pidStr = createOutOrderProBean.getPid();
            String num = createOutOrderProBean.getNum();
            if (StringUtils.isBlank(pidStr) || StringUtils.isBlank(num)) return result(null, "参数错误");
            long pid = Utils.parseLong(pidStr);
            if (pid <= 0) return result(null, "参数错误");
            StProductsBO stProductsBO = productsService.selectByPrimaryKey((int) pid);
            OrderLineItemBean one = new OrderLineItemBean();
            one.setOrderLineNo(String.valueOf(i));
            // 货主编码
            one.setOwnerCode(JiuConfig.OWNER_CODE);
            // 商品编码
            // TODO this item code must be change in server
//                one.setItemCode(stProductsBO.getLogisticsGoodsCode());
            one.setItemCode("416");
            one.setPlanQty(num);
            orderLines.add(one);
            System.out.println(one.toString());
            System.out.println(stProductsBO.toString());
        }

        returnOrderCreateBean.setReturnOrder(returnOrderBean);
        returnOrderCreateBean.setOrderLines(orderLines);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", ReturnOrderCreateBean.class);
        xStream.alias("returnOrder", ReturnOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);

        String xml = xStream.toXML(returnOrderCreateBean);

        String url = JiuUtils.getRequestUrl("returnorder.create", xml);
        if (StringUtils.isBlank(url)) return result(null, "退货入库单创建失败");
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
        Date date = new Date();

        if (responseBean != null) {
            if ("100000".equals(responseBean.getCode())) {
                String returnOrderId = responseBean.getReturnOrderId();
                ReturnOrderBO returnOrderBO = new ReturnOrderBO();
                returnOrderBO.setOrderNum(orderNum);
                returnOrderBO.setPreDeliveryOrderNum(preDeliveryOrderCode);
                returnOrderBO.setSystemOrderId(returnOrderId);
                returnOrderBO.setOrderType(orderType);
                if (StringUtils.isNotBlank(logisticsCode)) returnOrderBO.setLogisticsCode(logisticsCode);
                if (StringUtils.isNotBlank(returnReason)) returnOrderBO.setReturnReason(returnReason);
                if (StringUtils.isNotBlank(buyerNick)) returnOrderBO.setBuyerNick(buyerNick);
                if (StringUtils.isNotBlank(remark)) returnOrderBO.setRemark(remark);

                if (StringUtils.isNotBlank(sendCompany)) returnOrderBO.setCompany(sendCompany);
                if (StringUtils.isNotBlank(sendName)) returnOrderBO.setName(sendName);
                if (StringUtils.isNotBlank(sendTel)) returnOrderBO.setTel(sendTel);
                if (StringUtils.isNotBlank(sendMobile)) returnOrderBO.setMobile(sendMobile);
                if (StringUtils.isNotBlank(sendEmail)) returnOrderBO.setEmail(sendEmail);
                if (StringUtils.isNotBlank(sendProvince)) returnOrderBO.setProvince(sendProvince);
                if (StringUtils.isNotBlank(sendCity)) returnOrderBO.setCity(sendCity);
                if (StringUtils.isNotBlank(sendArea)) returnOrderBO.setArea(sendArea);
                if (StringUtils.isNotBlank(sendDetailAddress)) returnOrderBO.setDetailaddress(sendDetailAddress);
                returnOrderBO.setCreateTime(date);
                returnOrderBO.setModifyTime(date);
                long id = returnOrderService.insertSelective(returnOrderBO);
                if (id > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CreateOutOrderProBean outOrderProBean = list.get(i);
                        String pidStr = outOrderProBean.getPid();
                        if(StringUtils.isBlank(pidStr)) return result(null, "退货入库单创建失败");
                        long pid = Utils.parseLong(pidStr);
                        if(pid < 1) return result(null, "退货入库单创建失败");;
                        StProductsBO stProductsBO = productsService.selectByPrimaryKey((int) pid);
                        ReturnOrderInfoBO returnOrderInfoBO = new ReturnOrderInfoBO();
                        returnOrderInfoBO.setReturnOrderId(id);
                        returnOrderInfoBO.setProductId(Utils.parseLong(outOrderProBean.getPid()));
                        returnOrderInfoBO.setOrderNum(orderNum);
                        returnOrderInfoBO.setGoodsCode(stProductsBO.getLogisticsGoodsCode());
                        returnOrderInfoBO.setSystemOrderId(returnOrderId);
                        returnOrderInfoBO.setPlanQty(outOrderProBean.getNum());
                        returnOrderInfoBO.setCreateTime(date);
                        returnOrderInfoBO.setModifyTime(date);
                        int low = returnOrderInfoService.insertSelective(returnOrderInfoBO);
                        if(low < 0) return result(null, "退货入库单创建失败");
                    }
                    return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
                }
            }
        }


        return result(null, "退货入库单创建失败");
    }

}
