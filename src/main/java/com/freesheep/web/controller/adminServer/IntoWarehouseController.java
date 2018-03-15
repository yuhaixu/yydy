package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.jiuBean.*;
import com.freesheep.biz.model.*;
import com.freesheep.biz.service.IntoWarehouseInfoService;
import com.freesheep.biz.service.IntoWarehouseOrderService;
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
@RequestMapping("/admin/into_warehouse")
public class IntoWarehouseController extends BaseSecretController {

    private Logger log = Logger.getLogger(IntoWarehouseController.class);

    @Resource
    StProductsService productsService;
    @Resource
    IntoWarehouseOrderService intoWarehouseOrderService;
    @Resource
    IntoWarehouseInfoService intoWarehouseInfoService;

    @ResponseBody
    @RequestMapping(value = "/rk_confirm", method = RequestMethod.POST, produces = "application/xml")
    public Object Confirm(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        log.debug("confirm");
        String body = getBodyInfo();
        log.debug("============StockoutOrderController confirm body start================");
        log.debug(body);
        log.debug("========StockoutOrderController confirm body end============");

        XStream xStream = JiuUtils.createXstream();
        xStream.alias("request", IntoWarehouseConfirmBean.class);
        xStream.alias("entryOrder", EntryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.processAnnotations(IntoWarehouseConfirmBean.class);
        IntoWarehouseConfirmBean intoWarehouseConfirmBean = (IntoWarehouseConfirmBean) xStream.fromXML(body);
        if (intoWarehouseConfirmBean != null) {
            EntryOrderBean entryOrder = intoWarehouseConfirmBean.getEntryOrder();
            if(entryOrder != null){
                String entryOrderCode = entryOrder.getEntryOrderCode();
                String entryOrderId = entryOrder.getEntryOrderId();
                String status = entryOrder.getStatus();
                String remark = entryOrder.getRemark();
                IntoWarehouseOrderBO intoWarehouseOrderBO = intoWarehouseOrderService.selectIntoWarehouseOrderByNum(entryOrderId);
                if(intoWarehouseOrderBO == null){
                    log.debug("===========错误的订单确认=============");
                    return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "402", "错误的订单确认"));
                }
                Date date = new Date();
                intoWarehouseOrderBO.setOrderStatus(entryOrder.getStatus());
                if(StringUtils.isNotBlank(remark)) intoWarehouseOrderBO.setSystemRemark(remark);
                intoWarehouseOrderBO.setModifyTime(date);
                int row = intoWarehouseOrderService.updateByPrimaryKeySelective(intoWarehouseOrderBO);
                if (row < 1) {
                    log.debug("===========数据插入错误=============");
                    return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "403", "数据插入错误"));
                }

                List<OrderLineItemBean> orderLineList = intoWarehouseConfirmBean.getOrderLines();

                for (int i = 0; i < orderLineList.size(); i++) {
                    OrderLineItemBean orderLineItemBean = orderLineList.get(i);
                    String itemCode = orderLineItemBean.getItemCode();
                    // TODO test item code 69180125
                    IntoWarehouseInfoBO intoWarehouseInfoBO = intoWarehouseInfoService.selectInfoByOrder(entryOrderCode, "69180125");
                    if(intoWarehouseInfoBO == null){
                        log.debug("===========数据存储错误=============");
                        return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "406", "数据存储错误"));
                    }
                    intoWarehouseInfoBO.setActualReceiveNum(orderLineItemBean.getActualQty());
                    intoWarehouseInfoBO.setInventoryType(orderLineItemBean.getInventoryType());
                    intoWarehouseInfoBO.setItemCode(orderLineItemBean.getItemCode());
                    intoWarehouseInfoBO.setItemId(orderLineItemBean.getItemId());
                    intoWarehouseInfoBO.setSystemRemark(orderLineItemBean.getRemark());
                    intoWarehouseInfoBO.setModifyTime(date);
                    int upLow = intoWarehouseInfoService.updateByPrimaryKeySelective(intoWarehouseInfoBO);
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
//            parMap = getBodyMap(Constant.ADMIN_REQUEST);
            parMap = testBody();
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

        // 采购单号
        String purchaseOrderCode = Utils.getSomeValue(parMap.get("purchase_order_code"));
        // 仓库编码
        String warehouseCode = Utils.getSomeValue(parMap.get("warehouse_code"));
        // 业务类型
        String orderType = Utils.getSomeValue(parMap.get("order_type"));
        String remark = Utils.getSomeValue(parMap.get("remark"));

        String sendCompany = Utils.getSomeValue(parMap.get("send_company"));
        String sendName = Utils.getSomeValue(parMap.get("send_name"));
        String sendTel = Utils.getSomeValue(parMap.get("send_tel"));
        String sendMobile = Utils.getSomeValue(parMap.get("send_mobile"));
        String sendEmail = Utils.getSomeValue(parMap.get("send_email"));
        String sendProvince = Utils.getSomeValue(parMap.get("send_province"));
        String sendCity = Utils.getSomeValue(parMap.get("send_city"));
        String sendArea = Utils.getSomeValue(parMap.get("send_area"));
        String sendDetailAddress = Utils.getSomeValue(parMap.get("send_detail_address"));
        // 预期到货时间
        String expectStartTime = Utils.getSomeValue(parMap.get("expect_start_time"));
        // 最迟预期到货时间
        String expectEndTime = Utils.getSomeValue(parMap.get("expect_end_time"));

        Date startDate = null;
        Date endDate = null;
        if (StringUtils.isNotBlank(expectStartTime)) {
            startDate = DateUtils.fromTimer(expectStartTime);
            if(startDate == null) return result(null, "参数错误");
        }
        if (StringUtils.isNotBlank(expectEndTime)) {
            endDate = DateUtils.fromTimer(expectEndTime);
            if(startDate == null) return result(null, "参数错误");
        }

        String receiverCompany = Utils.getSomeValue(parMap.get("receiver_company"));
        String receiverName = Utils.getSomeValue(parMap.get("receiver_name"));
        String receiverTel = Utils.getSomeValue(parMap.get("receiver_tel"));
        String receiverMobile = Utils.getSomeValue(parMap.get("receiver_mobile"));
        String receiverEmail = Utils.getSomeValue(parMap.get("receiver_email"));
        String receiverProvince = Utils.getSomeValue(parMap.get("receiver_province"));
        String receiverCity = Utils.getSomeValue(parMap.get("receiver_city"));
        String receiverArea = Utils.getSomeValue(parMap.get("receiver_area"));
        String receiverDetailAddress = Utils.getSomeValue(parMap.get("receiver_detail_address"));

        if (StringUtils.isBlank(warehouseCode) || StringUtils.isBlank(orderType)){
            result(null, "参数错误");
        }


        // 出库单创建
        IntoWarehouseCreateBean intoWarehouseCreateBean = new IntoWarehouseCreateBean();
        String orderNum = "RK" + OrderUtils.getOrder();
        EntryOrderBean entryOrdeBean = new EntryOrderBean();
        entryOrdeBean.setEntryOrderCode(orderNum);
        entryOrdeBean.setOwnerCode(JiuConfig.OWNER_CODE);
        entryOrdeBean.setWarehouseCode(warehouseCode);
        if (StringUtils.isNotBlank(purchaseOrderCode)) entryOrdeBean.setPurchaseOrderCode(purchaseOrderCode);
        if (StringUtils.isNotBlank(expectStartTime)) entryOrdeBean.setExpectStartTime(expectStartTime);
        if (StringUtils.isNotBlank(expectEndTime)) entryOrdeBean.setExpectEndTime(expectEndTime);
        if (StringUtils.isNotBlank(remark)) entryOrdeBean.setRemark(remark);
        entryOrdeBean.setOrderType(orderType);

        SenderInfoBean senderInfoBean = new SenderInfoBean();
        if (StringUtils.isNotBlank(sendCompany)) senderInfoBean.setCompany(sendCompany);
        if (StringUtils.isNotBlank(sendName)) senderInfoBean.setName(sendName);
        if (StringUtils.isNotBlank(sendTel)) senderInfoBean.setTel(sendTel);
        if (StringUtils.isNotBlank(sendMobile)) senderInfoBean.setMobile(sendMobile);
        if (StringUtils.isNotBlank(sendEmail)) senderInfoBean.setEmail(sendEmail);
        if (StringUtils.isNotBlank(sendProvince)) senderInfoBean.setProvince(sendProvince);
        if (StringUtils.isNotBlank(sendCity)) senderInfoBean.setCity(sendCity);
        if (StringUtils.isNotBlank(sendArea)) senderInfoBean.setArea(sendArea);
        if (StringUtils.isNotBlank(sendDetailAddress)) senderInfoBean.setDetailAddress(sendDetailAddress);
        entryOrdeBean.setSenderInfo(senderInfoBean);

        ReceiverInfoBean receiverInfoBean = new ReceiverInfoBean();
        if (StringUtils.isNotBlank(receiverCompany)) receiverInfoBean.setCompany(receiverCompany);
        if (StringUtils.isNotBlank(receiverName)) receiverInfoBean.setName(receiverName);
        if (StringUtils.isNotBlank(receiverTel)) receiverInfoBean.setTel(receiverTel);
        if (StringUtils.isNotBlank(receiverMobile)) receiverInfoBean.setMobile(receiverMobile);
        if (StringUtils.isNotBlank(receiverEmail)) receiverInfoBean.setEmail(receiverEmail);
        if (StringUtils.isNotBlank(receiverProvince)) receiverInfoBean.setProvince(receiverProvince);
        if (StringUtils.isNotBlank(receiverCity)) receiverInfoBean.setCity(receiverCity);
        if (StringUtils.isNotBlank(receiverArea)) receiverInfoBean.setArea(receiverArea);
        if (StringUtils.isNotBlank(receiverDetailAddress)) receiverInfoBean.setDetailAddress(receiverDetailAddress);
        entryOrdeBean.setReceiverInfo(receiverInfoBean);


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

        intoWarehouseCreateBean.setEntryOrder(entryOrdeBean);
        intoWarehouseCreateBean.setOrderLines(orderLines);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", IntoWarehouseCreateBean.class);
        xStream.alias("entryOrder", EntryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);
        xStream.alias("receiverInfo", ReceiverInfoBean.class);

        String xml = xStream.toXML(intoWarehouseCreateBean);

        String url = JiuUtils.getRequestUrl("entryorder.create", xml);
        if (StringUtils.isBlank(url)) return result(null, "入库单创建失败");
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
                String entryOrderId = responseBean.getEntryOrderId();
                IntoWarehouseOrderBO intoWarehouseOrderBO = new IntoWarehouseOrderBO();
                intoWarehouseOrderBO.setOrderNum(orderNum);
                intoWarehouseOrderBO.setSystemOrderNum(entryOrderId);
                if (StringUtils.isNotBlank(purchaseOrderCode))  intoWarehouseOrderBO.setPurchaseOrderCode(purchaseOrderCode);
                if (StringUtils.isNotBlank(orderType)) intoWarehouseOrderBO.setOrderType(orderType);
                if (StringUtils.isNotBlank(warehouseCode)) intoWarehouseOrderBO.setWarehouseCode(warehouseCode);
                if (startDate != null) intoWarehouseOrderBO.setExpectStartTime(startDate);
                if (endDate != null) intoWarehouseOrderBO.setExpectEndTime(endDate);

                if (StringUtils.isNotBlank(sendCompany)) intoWarehouseOrderBO.setSendCompany(sendCompany);
                if (StringUtils.isNotBlank(sendName)) intoWarehouseOrderBO.setSendName(sendName);
                if (StringUtils.isNotBlank(sendTel)) intoWarehouseOrderBO.setSendTel(sendTel);
                if (StringUtils.isNotBlank(sendMobile)) intoWarehouseOrderBO.setSendMobile(sendMobile);
                if (StringUtils.isNotBlank(sendEmail)) intoWarehouseOrderBO.setSendEmail(sendEmail);
                if (StringUtils.isNotBlank(sendProvince)) intoWarehouseOrderBO.setSendProvince(sendProvince);
                if (StringUtils.isNotBlank(sendCity)) intoWarehouseOrderBO.setSendCity(sendCity);
                if (StringUtils.isNotBlank(sendArea)) intoWarehouseOrderBO.setSendArea(sendArea);
                if (StringUtils.isNotBlank(sendDetailAddress)) intoWarehouseOrderBO.setSendDetailAddress(sendDetailAddress);
                if (StringUtils.isNotBlank(receiverCompany)) intoWarehouseOrderBO.setReceiverCompany(receiverCompany);
                if (StringUtils.isNotBlank(receiverName)) intoWarehouseOrderBO.setReceiverName(receiverName);
                if (StringUtils.isNotBlank(receiverTel)) intoWarehouseOrderBO.setReceiverTel(receiverTel);
                if (StringUtils.isNotBlank(receiverMobile)) intoWarehouseOrderBO.setReceiverMobile(receiverMobile);
                if (StringUtils.isNotBlank(receiverEmail)) intoWarehouseOrderBO.setReceiverEmail(receiverEmail);
                if (StringUtils.isNotBlank(receiverProvince)) intoWarehouseOrderBO.setReceiverProvince(receiverProvince);
                if (StringUtils.isNotBlank(receiverCity)) intoWarehouseOrderBO.setReceiverCity(receiverCity);
                if (StringUtils.isNotBlank(receiverArea)) intoWarehouseOrderBO.setReceiverArea(receiverArea);
                if (StringUtils.isNotBlank(receiverDetailAddress)) intoWarehouseOrderBO.setReceiverDetailAddress(receiverDetailAddress);
                if (StringUtils.isNotBlank(remark)) intoWarehouseOrderBO.setRemark(remark);

                intoWarehouseOrderBO.setCreateTime(date);
                intoWarehouseOrderBO.setModifyTime(date);
                long id = intoWarehouseOrderService.insertSelective(intoWarehouseOrderBO);
                if (id > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CreateOutOrderProBean outOrderProBean = list.get(i);
                        String pidStr = outOrderProBean.getPid();
                        if(StringUtils.isBlank(pidStr)) return result(null, "入库单创建失败");
                        long pid = Utils.parseLong(pidStr);
                        if(pid < 1) return result(null, "入库单创建失败");;
                        StProductsBO stProductsBO = productsService.selectByPrimaryKey((int) pid);
                        IntoWarehouseInfoBO warehouseInfoBO = new IntoWarehouseInfoBO();
                        warehouseInfoBO.setIntoWarehouseOrderId(id);
                        warehouseInfoBO.setProductId(Utils.parseLong(outOrderProBean.getPid()));
                        warehouseInfoBO.setProductId(pid);
                        warehouseInfoBO.setReceiveNum(outOrderProBean.getNum());
                        warehouseInfoBO.setOrderNum(orderNum);
                        warehouseInfoBO.setGoodsCode(stProductsBO.getLogisticsGoodsCode());
                        warehouseInfoBO.setCreateTime(date);
                        warehouseInfoBO.setModifyTime(date);
                        int low = intoWarehouseInfoService.insertSelective(warehouseInfoBO);
                        if(low < 0) return result(null, "入库单创建失败");
                    }
                    return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
                }
            }
        }


        return result(null, "入库单创建失败");
    }

}
