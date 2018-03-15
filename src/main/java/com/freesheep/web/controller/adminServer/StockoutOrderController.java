package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.jiuBean.*;
import com.freesheep.biz.model.OutgoingOrderBO;
import com.freesheep.biz.model.OutgoingProductInfoBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.OutgoingOrderService;
import com.freesheep.biz.service.OutgoingProductInfoService;
import com.freesheep.biz.service.StProductsService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.*;
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
@RequestMapping("/admin/stockout")
public class StockoutOrderController extends BaseSecretController {

    private Logger log = Logger.getLogger(StockoutOrderController.class);

    @Resource
    StProductsService productsService;
    @Resource
    OutgoingOrderService outgoingOrderService;
    @Resource
    OutgoingProductInfoService productInfoService;

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
        xStream.alias("request", ConfirmOrderBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("package", PackageBean.class);
        xStream.alias("item", ItemBean.class);
        xStream.alias("batch", BatchBean.class);
        xStream.processAnnotations(ConfirmOrderBean.class);
        ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) xStream.fromXML(body);
        if (confirmOrderBean != null) {
            DeliveryOrderBean deliveryOrderBean = confirmOrderBean.getDeliveryOrder();
            if(deliveryOrderBean != null){
                String deliveryOrderId = deliveryOrderBean.getDeliveryOrderId();
                String deliveryOrderCode = deliveryOrderBean.getDeliveryOrderCode();
                OutgoingOrderBO outgoingOrderBO = outgoingOrderService.selectOutgoingOrderByDeliveryOrderCode(deliveryOrderId);
                if(outgoingOrderBO == null){
                    log.debug("===========错误的订单确认=============");
                    return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "402", "错误的订单确认"));
                }
                Date date = new Date();
                outgoingOrderBO.setOrderStatus(deliveryOrderBean.getStatus());
                outgoingOrderBO.setLogisticsName(deliveryOrderBean.getLogisticsName());
                outgoingOrderBO.setExpressCode(deliveryOrderBean.getExpressCode());
                outgoingOrderBO.setModifyTime(date);
                int row = outgoingOrderService.updateByPrimaryKeySelective(outgoingOrderBO);
                if (row < 1) {
                    log.debug("===========数据插入错误=============");
                    return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "403", "数据插入错误"));
                }

                List<OrderLineItemBean> orderLineList = confirmOrderBean.getOrderLines();

                for (int i = 0; i < orderLineList.size(); i++) {
                    OrderLineItemBean orderLineItemBean = orderLineList.get(i);
                    String itemCode = orderLineItemBean.getItemCode();
                    // TODO test item code 69180125
                    OutgoingProductInfoBO productInfoBO = productInfoService.selectInfoByOrder(deliveryOrderCode, "69180125");
                    if(productInfoBO == null){
                        log.debug("===========数据存储错误=============");
                        return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "406", "数据存储错误"));
                    }
                    productInfoBO.setSystemOrderNum(deliveryOrderId);
                    productInfoBO.setActualSendNum(orderLineItemBean.getActualQty());
                    productInfoBO.setInventoryType(orderLineItemBean.getInventoryType());
                    productInfoBO.setItemCode(orderLineItemBean.getItemCode());
                    productInfoBO.setItemId(orderLineItemBean.getItemId());
                    productInfoBO.setModifyTime(date);
                    int upLow = productInfoService.updateByPrimaryKeySelective(productInfoBO);
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
    public ResultView create(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        Map<String, Object> parMap = null;
        try {
            parMap = getBodyMap(Constant.ADMIN_REQUEST);
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
        // 出库单类型 必填
        String orderType = Utils.getSomeValue(parMap.get("order_type"));
        // 仓库编码
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        // 要求出库时间
        String scheduleDate = Utils.getSomeValue(parMap.get("schedule_date"));
        String sendCompany = Utils.getSomeValue(parMap.get("send_company"));
        String sendName = Utils.getSomeValue(parMap.get("send_name"));
        String sendTel = Utils.getSomeValue(parMap.get("send_tel"));
        String sendMobile = Utils.getSomeValue(parMap.get("send_mobile"));
        String sendId = Utils.getSomeValue(parMap.get("send_id"));
        String sendCarNo = Utils.getSomeValue(parMap.get("send_car_no"));
        String receiveCompany = Utils.getSomeValue(parMap.get("receive_company"));
        String receiveName = Utils.getSomeValue(parMap.get("receive_name"));
        String receiveTel = Utils.getSomeValue(parMap.get("receive_tel"));
        String receiveMobile = Utils.getSomeValue(parMap.get("receive_mobile"));
        String receiveEmail = Utils.getSomeValue(parMap.get("receive_email"));
        String receiveProvince = Utils.getSomeValue(parMap.get("receive_province"));
        String receiveCity = Utils.getSomeValue(parMap.get("receive_city"));
        String receiveArea = Utils.getSomeValue(parMap.get("receive_area"));
        String receiveDetailAddress = Utils.getSomeValue(parMap.get("receive_detail_address"));
        String receiveId = Utils.getSomeValue(parMap.get("receive_id"));
        String receiveRemark = Utils.getSomeValue(parMap.get("receive_remark"));
        String logisticsCode = Utils.getSomeValue(parMap.get("logistics_code"));
        if (StringUtils.isBlank(orderType) || StringUtils.isBlank(receiveName) || StringUtils.isBlank(receiveMobile) || StringUtils.isBlank(warehouseCode) ||
                StringUtils.isBlank(receiveProvince) || StringUtils.isBlank(receiveCity) || StringUtils.isBlank(receiveDetailAddress) || StringUtils.isBlank(logisticsCode)) {
            return result(null, "参数错误");
        }


        String dateStr = DateUtils.getNowTime();
        // 出库单创建
        OutgoingOrderCreateBean outgoingOrderCreateBean = new OutgoingOrderCreateBean();
        String orderNum = OrderUtils.getOrder();
        DeliveryOrderBean deliveryOrder = new DeliveryOrderBean();
        deliveryOrder.setDeliveryOrderCode(orderNum);
        deliveryOrder.setOrderType(orderType);
        deliveryOrder.setLogisticsCode(logisticsCode);
        deliveryOrder.setWarehouseCode(warehouseCode);
        // 出库单创建时间
        deliveryOrder.setCreateTime(dateStr);
        // 要求出库时间
        if (StringUtils.isNotBlank(scheduleDate)) deliveryOrder.setScheduleDate(scheduleDate);
        if (StringUtils.isNotBlank(receiveRemark)) deliveryOrder.setRemark(receiveRemark);


        PickerInfoBean pickerInfoBean = new PickerInfoBean();
        if (StringUtils.isNotBlank(sendCompany)) pickerInfoBean.setCompany(sendCompany);
        if (StringUtils.isNotBlank(sendName)) pickerInfoBean.setName(sendName);
        if (StringUtils.isNotBlank(sendTel)) pickerInfoBean.setTel(sendTel);
        if (StringUtils.isNotBlank(sendMobile)) pickerInfoBean.setMobile(sendMobile);
        if (StringUtils.isNotBlank(sendId)) pickerInfoBean.setId(sendId);
        if (StringUtils.isNotBlank(sendCarNo)) pickerInfoBean.setCarNo(sendCarNo);
        deliveryOrder.setPickerInfo(pickerInfoBean);

        SenderInfoBean senderInfoBean = new SenderInfoBean();
        if (StringUtils.isNotBlank(sendCompany)) senderInfoBean.setCompany(sendCompany);
        if (StringUtils.isNotBlank(sendName)) senderInfoBean.setName(sendName);
        if (StringUtils.isNotBlank(sendTel)) senderInfoBean.setTel(sendTel);
        if (StringUtils.isNotBlank(sendId)) senderInfoBean.setId(sendId);
        deliveryOrder.setSenderInfo(senderInfoBean);

        ReceiverInfoBean receiverInfoBean = new ReceiverInfoBean();
        if (StringUtils.isNotBlank(receiveCompany)) receiverInfoBean.setCompany(receiveCompany);
        receiverInfoBean.setName(receiveName);
        if (StringUtils.isNotBlank(receiveTel)) receiverInfoBean.setTel(receiveTel);
        receiverInfoBean.setMobile(receiveMobile);
        if (StringUtils.isNotBlank(receiveEmail)) receiverInfoBean.setEmail(receiveEmail);
        receiverInfoBean.setProvince(receiveProvince);
        receiverInfoBean.setCity(receiveCity);
        receiverInfoBean.setArea(receiveArea);
        receiverInfoBean.setDetailAddress(receiveDetailAddress);
        if (StringUtils.isNotBlank(receiveId)) receiverInfoBean.setId(receiveId);
        deliveryOrder.setReceiverInfo(receiverInfoBean);


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
            one.setItemName(stProductsBO.getProducts());
            one.setPlanQty(num);
            orderLines.add(one);
            System.out.println(one.toString());
            System.out.println(stProductsBO.toString());
        }

        outgoingOrderCreateBean.setDeliveryOrder(deliveryOrder);
        outgoingOrderCreateBean.setOrderLines(orderLines);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", OutgoingOrderCreateBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);
        xStream.alias("receiverInfo", ReceiverInfoBean.class);

        String xml = xStream.toXML(outgoingOrderCreateBean);

        String url = JiuUtils.getRequestUrl("stockout.create", xml);
        if (StringUtils.isBlank(url)) return result(null, "出库单创建失败");
        log.debug("===================StockoutOrderController requet url===========================");
        log.debug(url);
        log.debug("====================StockoutOrderController requet url==========================");

        log.debug("===================StockoutOrderController body===========================");
        log.debug(xml);
        log.debug("====================StockoutOrderController body==========================");

        String jiuResp = new HttpRequest().xmlPost(url, xml);
        log.debug("===================StockoutOrderController response===========================");
        log.debug(jiuResp);
        log.debug("====================StockoutOrderController response==========================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);
        Date date = new Date();

        if (responseBean != null) {
            if ("100000".equals(responseBean.getCode())) {
                String deliveryOrderId = responseBean.getDeliveryOrderId();
                OutgoingOrderBO outgoingOrderBO = new OutgoingOrderBO();
                outgoingOrderBO.setOrderNum(orderNum);
                outgoingOrderBO.setSystemOrderNum(deliveryOrderId);
                outgoingOrderBO.setOrderType(orderType);
                outgoingOrderBO.setWarehouseCode(warehouseCode);
                if (StringUtils.isNotBlank(scheduleDate)) outgoingOrderBO.setScheduleDate(scheduleDate);
                if (StringUtils.isNotBlank(sendCompany)) outgoingOrderBO.setSendCompany(sendCompany);
                if (StringUtils.isNotBlank(sendName)) outgoingOrderBO.setSendName(sendName);
                if (StringUtils.isNotBlank(sendTel)) outgoingOrderBO.setSendTel(sendTel);
                if (StringUtils.isNotBlank(sendMobile)) outgoingOrderBO.setSendMobile(sendMobile);
                if (StringUtils.isNotBlank(sendId)) outgoingOrderBO.setSendId(sendId);
                if (StringUtils.isNotBlank(sendCarNo)) outgoingOrderBO.setSendCarNo(sendCarNo);
                if (StringUtils.isNotBlank(receiveCompany)) outgoingOrderBO.setReceiveCompany(receiveCompany);
                if (StringUtils.isNotBlank(receiveName)) outgoingOrderBO.setReceiveName(receiveName);
                if (StringUtils.isNotBlank(receiveTel)) outgoingOrderBO.setReceiveTel(receiveTel);
                if (StringUtils.isNotBlank(receiveMobile)) outgoingOrderBO.setReceiveMobile(receiveMobile);
                if (StringUtils.isNotBlank(receiveEmail)) outgoingOrderBO.setReceiveEmail(receiveEmail);
                if (StringUtils.isNotBlank(receiveProvince)) outgoingOrderBO.setProvince(receiveProvince);
                if (StringUtils.isNotBlank(receiveCity)) outgoingOrderBO.setCity(receiveCity);
                if (StringUtils.isNotBlank(receiveArea)) outgoingOrderBO.setArea(receiveArea);
                if (StringUtils.isNotBlank(receiveDetailAddress)) outgoingOrderBO.setDetailAddress(receiveDetailAddress);
                if (StringUtils.isNotBlank(receiveId)) outgoingOrderBO.setReceiveId(receiveId);
                if (StringUtils.isNotBlank(receiveRemark)) outgoingOrderBO.setRemark(receiveRemark);
                outgoingOrderBO.setLogisticsCode(logisticsCode);
                outgoingOrderBO.setCreateTime(date);
                outgoingOrderBO.setModifyTime(date);
                long id = outgoingOrderService.insertSelective(outgoingOrderBO);
                if (id > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CreateOutOrderProBean outOrderProBean = list.get(i);
                        String pidStr = outOrderProBean.getPid();
                        if(StringUtils.isBlank(pidStr)) return result(null, "出库单创建失败");;
                        long pid = Utils.parseLong(pidStr);
                        if(pid < 1) return result(null, "出库单创建失败");;
                        StProductsBO stProductsBO = productsService.selectByPrimaryKey((int) pid);
                        OutgoingProductInfoBO productInfoBO = new OutgoingProductInfoBO();
                        productInfoBO.setOutgoingOrderId(id);
                        productInfoBO.setProductId(Utils.parseLong(outOrderProBean.getPid()));
                        productInfoBO.setSendNum(outOrderProBean.getNum());
                        productInfoBO.setOrderNum(orderNum);
                        productInfoBO.setGoodsCode(stProductsBO.getLogisticsGoodsCode());
                        productInfoBO.setCreateTime(date);
                        productInfoBO.setModifyTime(date);
                        int low = productInfoService.insertSelective(productInfoBO);
                        if(low < 0) return result(null, "出库单创建失败");
                    }
                    return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
                }
            }
        }

        return result(null, "出库单创建失败");
    }

}
