package com.freesheep.web.controller.adminServer;

import com.freesheep.biz.jiuBean.*;
import com.freesheep.biz.model.LogisticsInfoBO;
import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.LogisticsInfoService;
import com.freesheep.biz.service.StOrderAddressesService;
import com.freesheep.biz.service.StOrdersService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.web.config.jiuConfig.JiuConfig;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.DateUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/admin/send_order")
public class CreateSendOrderController extends BaseSecretController {

    private Logger log = Logger.getLogger(CreateSendOrderController.class);

    @Resource
    LogisticsInfoService logisticsInfoService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultView create(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        Map<String, Object> parMap = getBodyMap(Constant.ADMIN_REQUEST);
        if(parMap == null) return result(null, "参数错误");
        String oidStr = Utils.getSomeValue(parMap.get("oid"));

//        String oidStr = Utils.getSomeValue(request.getParameter("oid"));

        if (StringUtils.isBlank(oidStr)) {
            return result(null, "参数错误");
        }

        long oid = Utils.parseLong(oidStr);
        if (oid < 1) return result(null, "参数错误");

        String create = createSendOrder(oid);
        log.debug("==============request body================");
        log.debug(create);
        log.debug("===============request body===============");
        String url = JiuUtils.getRequestUrl("deliveryorder.create", create);
        String jiuResp = new HttpRequest().xmlPost(url, create);
        log.debug("==============================================");
        log.debug(jiuResp);
        log.debug("==============================================");
        System.out.println("==============================================");
        System.out.println(jiuResp);
        System.out.println("==============================================");

        ResponseBean responseBean = JiuUtils.getResponse(jiuResp);

        if (responseBean != null) {
            if ("100000".equals(responseBean.getCode())) {
                String deliveryOrderId = responseBean.getDeliveryOrderId();
                LogisticsInfoBO logisticsInfoBO = logisticsInfoService.selectInfoByDeliveryOrderId(deliveryOrderId);
                Date date = new Date();
                int low = 0;
                if (logisticsInfoBO == null || (logisticsInfoBO != null && logisticsInfoBO.getOrderId() != oid)) {
                    LogisticsInfoBO insert = new LogisticsInfoBO();
                    insert.setDeliveryOrderId(deliveryOrderId);
                    insert.setOrderId(oid);
                    insert.setOrderNum(oidStr);
                    insert.setCreateTime(date);
                    insert.setModifyTime(date);
                    low = logisticsInfoService.insertSelective(insert);
                } else {
                    logisticsInfoBO.setDeliveryOrderId(deliveryOrderId);
                    logisticsInfoBO.setModifyTime(date);
                    low = logisticsInfoService.updateByPrimaryKeySelective(logisticsInfoBO);
                }
                if (low > 0) return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.ADMIN_REQUEST));
            }
        }
        return result(null, "发货单创建失败");
    }


    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST, produces = "application/xml")
    public Object Confirm(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        log.debug("confirm");
        String body = getBodyInfo();
        log.debug("=================body start===================");
        log.debug(body);
        log.debug("=================body end===================");

        XStream xStream = JiuUtils.createXstream();
        xStream.alias("request", ConfirmOrderBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("package", PackageBean.class);
        xStream.alias("item", ItemBean.class);
        xStream.alias("batch", BatchBean.class);
        xStream.processAnnotations(ConfirmOrderBean.class);
        ConfirmOrderBean confirmOrderBean = (ConfirmOrderBean) xStream.fromXML(body);
        System.out.println(confirmOrderBean.toString());

        if (confirmOrderBean != null) {
            DeliveryOrderBean deliveryOrderBean = confirmOrderBean.getDeliveryOrder();
            List<PackageBean> packages = confirmOrderBean.getPackages();
            if (deliveryOrderBean != null && packages != null && packages.size() > 0) {
                String deliveryOrderId = deliveryOrderBean.getDeliveryOrderId();
                LogisticsInfoBO logisticsInfoBO = logisticsInfoService.selectInfoByDeliveryOrderId(deliveryOrderId);
                if (logisticsInfoBO == null) {
                    log.debug("===========错误的订单确认=============");
                    return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "402", "错误的订单确认"));
                }
                logisticsInfoBO.setDeliveryOrderId(deliveryOrderBean.getDeliveryOrderId());
                logisticsInfoBO.setStatus(deliveryOrderBean.getStatus());
                logisticsInfoBO.setOutBizCode(deliveryOrderBean.getOutBizCode());
                logisticsInfoBO.setConfirmType(deliveryOrderBean.getConfirmType());
                logisticsInfoBO.setOrderConfirmTime(deliveryOrderBean.getOrderConfirmTime());

                PackageBean packageBean = packages.get(0);
                Date date = new Date();
                long oid = logisticsInfoBO.getOrderId();
                StOrdersBO ordersBO = ordersService.selectByPrimaryKey((int) oid);

                logisticsInfoBO.setLogisticsCode(packageBean.getLogisticsCode());
                logisticsInfoBO.setLogisticsName(packageBean.getLogisticsName());
                logisticsInfoBO.setExpressCode(packageBean.getExpressCode());

                ordersBO.setExpressName(packageBean.getLogisticsName());
                ordersBO.setExpressCode(packageBean.getExpressCode());
                ordersBO.setOrderStatus(2);
                ordersBO.setModified(date);
                ordersService.updateByPrimaryKeySelective(ordersBO);


                logisticsInfoBO.setModifyTime(date);
                logisticsInfoService.updateByPrimaryKeySelective(logisticsInfoBO);
            } else {
                log.debug("===========接收数据有问题=============");
                return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "401", "接收数据有问题"));
            }


        } else {
            log.debug("===========未接收到返回数据=============");
            return JiuUtils.getResponseXml(JiuUtils.getBean("failure", "400", "未接收到返回数据"));
        }

        log.debug("===========success=============");
        String returnXml = JiuUtils.getResponseXml(JiuUtils.getBean("success", "200", null));
        log.debug("==============success return xml====================");
        log.debug(returnXml);
        log.debug("==============success return xml====================");
        return returnXml;
    }



    @Resource
    StOrdersService ordersService;

    public String createSendOrder(long oid) {
        StOrdersBO ordersBO = ordersService.selectByPrimaryKey((int) oid);
        StOrdersBO detail = ordersService.getOrderDetails(oid);
        // 发货单创建
        SendOrderCreateBean sendOrderCreateBean = new SendOrderCreateBean();
        DeliveryOrderBean deliveryOrder = new DeliveryOrderBean();
        deliveryOrder.setDeliveryOrderCode(String.valueOf(oid));
        deliveryOrder.setOrderType(JiuConfig.ORDER_TYPE_JYCK);
        deliveryOrder.setWarehouseCode(JiuConfig.WAREHOUSE_CODE);
        deliveryOrder.setSourcePlatformCode(JiuConfig.SOURCE_PLATFORM_CODE);
        deliveryOrder.setSourcePlatformName("新巴尔虎科技");
        // 发货单创建时间
        deliveryOrder.setCreateTime(DateUtils.getNowTime());
        // 前台订单 (店铺订单) 创建时间 (下单时间)
        deliveryOrder.setPlaceOrderTime(DateUtils.getNowTime());
        deliveryOrder.setPayTime(DateUtils.getNowTime());
        deliveryOrder.setOperateTime(DateUtils.getNowTime());
        deliveryOrder.setShopNick("新巴尔虎科技");
        deliveryOrder.setSellerNick("羊羊得意");
        // 买家昵称
        deliveryOrder.setBuyerNick(detail.getRecipients());
        deliveryOrder.setTotalAmount(String.valueOf(ordersBO.getAmount()));
        deliveryOrder.setGotAmount(String.valueOf(ordersBO.getAmount()));
        deliveryOrder.setFreight("0");
        deliveryOrder.setLogisticsCode(JiuConfig.LOGISTICS_CODE);
        deliveryOrder.setRemark(ordersBO.getRemark());

        SenderInfoBean senderInfoBean = new SenderInfoBean();
        senderInfoBean.setName("羊羊得意");
        senderInfoBean.setMobile("15811369402");
        senderInfoBean.setProvince("北京市");
        senderInfoBean.setCity("北京市");
        senderInfoBean.setArea("海淀区");
        senderInfoBean.setDetailAddress("中关村南大街48号九龙商务中心B座431");
        deliveryOrder.setSenderInfo(senderInfoBean);

        ReceiverInfoBean receiverInfoBean = new ReceiverInfoBean();
        receiverInfoBean.setName(detail.getRecipients());
        receiverInfoBean.setMobile(detail.getMobile());
        receiverInfoBean.setProvince(detail.getProvince());
        receiverInfoBean.setCity(detail.getCity());
        receiverInfoBean.setArea(detail.getCounty());
        receiverInfoBean.setDetailAddress(detail.getDetail());
        deliveryOrder.setReceiverInfo(receiverInfoBean);


        List<OrderLineItemBean> orderLines = new ArrayList<>();
        List<StProductsBO> productList = detail.getProductList();
        if (productList != null && productList.size() > 0) {
            for (int i = 0; i < productList.size(); i++) {
                StProductsBO stProductsBO = productList.get(i);
                OrderLineItemBean one = new OrderLineItemBean();
                one.setOrderLineNo(String.valueOf(i));
                // 货主编码
                one.setOwnerCode(JiuConfig.OWNER_CODE);
                // 商品编码
                // TODO this item code must be change in server
//                one.setItemCode(stProductsBO.getLogisticsGoodsCode());
                one.setItemCode("416");
                one.setItemName(stProductsBO.getProducts());
                one.setPlanQty(String.valueOf(stProductsBO.getPayNumber()));
                one.setActualPrice(String.valueOf(stProductsBO.getSale()));
                orderLines.add(one);
                System.out.println(one.toString());
                System.out.println(stProductsBO.toString());
            }
        }

        sendOrderCreateBean.setDeliveryOrder(deliveryOrder);
        sendOrderCreateBean.setOrderLines(orderLines);

        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request", SendOrderCreateBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);
        xStream.alias("receiverInfo", ReceiverInfoBean.class);

        String xml = xStream.toXML(sendOrderCreateBean);
        return xml;
    }

}
