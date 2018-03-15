package com.freesheep.web.test;


import com.freesheep.biz.jiuBean.*;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.DateUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class TestJir {

    @RequestMapping(value = "/test_xml", produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public Object testXml(){

        String create = createSendOrder();

        Map<String, String> map = new HashMap<>();
        map.put("method", "deliveryorder.create");
        map.put("timestamp", DateUtils.getNowTime());
        map.put("format", "xml");
        map.put("app_key", "f737f586-119b-4359-9d26-081374b2881b");
        map.put("v", "2.0");
        map.put("sign_method", "md5");
        map.put("customerId", "jhyd");

        Set<String> keySet = map.keySet();
        Iterator<String> iterable = keySet.iterator();
        List<String> keyList = new ArrayList<>();
        while (iterable.hasNext()){
            String key = iterable.next();
            keyList.add(key);
        }

        keyList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        StringBuilder body = new StringBuilder();
        StringBuilder bodyInfo = new StringBuilder();
        bodyInfo.append("e73a6317-e41f-473d-b8fd-a8e57c1b6dba");
        for (int i = 0; i < keyList.size(); i++){
            String key = keyList.get(i);
            body.append(key);
            body.append("=");
            body.append(map.get(key));
            body.append("&");
            bodyInfo.append(key);
            bodyInfo.append(map.get(key));
        }
        String bodyStr = body.toString().substring(0, body.length()-1);
        System.out.println("body = " + bodyStr);
        bodyInfo.append(create);
        bodyInfo.append("e73a6317-e41f-473d-b8fd-a8e57c1b6dba");
        System.out.println("bodyInfo = " + bodyInfo.toString());

        try {
            String secret = DigestUtils.encodeMD5Hex(bodyInfo.toString().getBytes("utf-8"));
            secret = secret.toUpperCase();
            System.out.println("secret = " + secret);
            bodyStr = bodyStr + "&sign=" + secret;
            System.out.println("bodyStr = " + bodyStr);
//            String sendBody = URLEncoder.encode(bodyStr, "utf-8");
            String sendBody = bodyStr.replace(" ", "%20");
            System.out.println("sendBody = " + sendBody);
            String url = "http://api.test.jiuyescm.com/v1/qimen/receive?" + sendBody;
            System.out.println("send url = " + url);
            String resp = new HttpRequest().xmlPost(url, create);
            System.out.println(resp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return create;
    }

    public String confirmSendOrder(){
        // 发货单确认
        ConfirmOrderBean confirmOrderBean = new ConfirmOrderBean();
        DeliveryOrderBean deliveryOrder = new DeliveryOrderBean();
        deliveryOrder.setDeliveryOrderCode("CK2017120452");
        deliveryOrder.setDeliveryOrderId("Z310000970499");
        deliveryOrder.setWarehouseCode("B01");
        deliveryOrder.setOrderType("JYCK");
        deliveryOrder.setStatus("DELIVERED");
        deliveryOrder.setOutBizCode("Z310000970499");
        deliveryOrder.setConfirmType("0");
        deliveryOrder.setOrderConfirmTime(DateUtils.getNowTime());
        deliveryOrder.setOperateTime(DateUtils.getNowTime());

        List<PackageBean> packages = new ArrayList<>();
        PackageBean packageBean = new PackageBean();
        packageBean.setLogisticsCode("JIUYE");
        packageBean.setLogisticsName("JIUYE");
        packageBean.setExpressCode("JY0002344212");
        List<ItemBean> items = new ArrayList<>();
        ItemBean item = new ItemBean();
        item.setItemCode("416");
        item.setQuantity("2");
        items.add(item);
        packageBean.setItems(items);
        packages.add(packageBean);


        List<OrderLineItemBean> orderLines = new ArrayList<>();
        OrderLineItemBean one = new OrderLineItemBean();
        one.setOrderLineNo("1");
        // 货主编码
        one.setOwnerCode("jhyd");
        // 商品编码
        one.setItemCode("416");
        one.setItemName("羊羊得意精品羊肉礼盒");
        one.setInventoryType("ZP");
        one.setPlanQty("2");
        one.setActualQty("2");
        List<BatchBean> batchs = new ArrayList<>();
        BatchBean batchBean = new BatchBean();
        batchBean.setInventoryType("ZP");
        batchBean.setActualQty("2");
        batchs.add(batchBean);
        one.setBatchs(batchs);
        orderLines.add(one);

        confirmOrderBean.setPackages(packages);
        confirmOrderBean.setDeliveryOrder(deliveryOrder);
        confirmOrderBean.setOrderLines(orderLines);


        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request",ConfirmOrderBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("package", PackageBean.class);
        xStream.alias("item", ItemBean.class);
        xStream.alias("batch", BatchBean.class);

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(confirmOrderBean);
        return xml;
    }


    public String createSendOrder(){
        // 发货单创建
        SendOrderCreateBean sendOrderCreateBean = new SendOrderCreateBean();
        DeliveryOrderBean deliveryOrder = new DeliveryOrderBean();
        deliveryOrder.setDeliveryOrderCode("CK2017120503");
        deliveryOrder.setOrderType("JYCK");
        deliveryOrder.setWarehouseCode("B01");
        deliveryOrder.setSourcePlatformCode("OTHER");
        deliveryOrder.setSourcePlatformName("新巴尔虎科技");
        // 发货单创建时间
        deliveryOrder.setCreateTime(DateUtils.getNowTime());
        // 前台订单 (店铺订单) 创建时间 (下单时间)
        deliveryOrder.setPlaceOrderTime(DateUtils.getNowTime());
        deliveryOrder.setPayTime(DateUtils.getNowTime());
        deliveryOrder.setOperateTime(DateUtils.getNowTime());
        deliveryOrder.setShopNick("新巴尔虎科技");
        deliveryOrder.setSellerNick("羊羊得意");
        deliveryOrder.setBuyerNick("testUser");
        deliveryOrder.setTotalAmount("518");
        deliveryOrder.setGotAmount("518");
        deliveryOrder.setFreight("0");
        deliveryOrder.setLogisticsCode("JIUYE");
        deliveryOrder.setRemark("remark info");


//        DeliveryRequirementsBean deliveryRequirementsBean = new DeliveryRequirementsBean();
//        deliveryRequirementsBean.setDeliveryType("PTPS");
//        deliveryOrder.setDeliveryRequirements(deliveryRequirementsBean);

        SenderInfoBean senderInfoBean = new SenderInfoBean();
        senderInfoBean.setName("于先生");
        senderInfoBean.setMobile("15811369402");
        senderInfoBean.setProvince("北京市");
        senderInfoBean.setCity("北京市");
        senderInfoBean.setArea("海淀区");
        senderInfoBean.setDetailAddress("中关村南大街48号九龙商务中心B座431");
        deliveryOrder.setSenderInfo(senderInfoBean);

        ReceiverInfoBean receiverInfoBean = new ReceiverInfoBean();
        receiverInfoBean.setName("test name");
        receiverInfoBean.setMobile("13800138000");
        receiverInfoBean.setProvince("北京市");
        receiverInfoBean.setCity("北京市");
        receiverInfoBean.setArea("西城区");
        receiverInfoBean.setDetailAddress("虎坊桥");
        deliveryOrder.setReceiverInfo(receiverInfoBean);


        List<OrderLineItemBean> orderLines = new ArrayList<>();
        OrderLineItemBean one = new OrderLineItemBean();
        one.setOrderLineNo("1");
        // 货主编码
        one.setOwnerCode("jhyd");
        // 商品编码
        one.setItemCode("416");
        one.setItemName("羊羊得意精品羊肉礼盒 ");
        one.setPlanQty("2");
        one.setActualPrice("518");
        orderLines.add(one);

        sendOrderCreateBean.setDeliveryOrder(deliveryOrder);
        sendOrderCreateBean.setOrderLines(orderLines);


        XStream xStream = new XStream(new DomDriver("utf-8"));
        xStream.alias("request",SendOrderCreateBean.class);
        xStream.alias("deliveryOrder", DeliveryOrderBean.class);
        xStream.alias("orderLine", OrderLineItemBean.class);
        xStream.alias("senderInfo", SenderInfoBean.class);
        xStream.alias("receiverInfo", ReceiverInfoBean.class);

        String xml = xStream.toXML(sendOrderCreateBean);
//        Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
//        Matcher m = p.matcher(xml);
//        String  finalresult = m.replaceAll("");
        return xml;
    }


    @RequestMapping(value = "/jiu", method = RequestMethod.POST, produces = "application/xml")
    @ResponseBody
    public Object getXml(@RequestBody Book book){
        System.out.println(book.toString());
        Product product = new Product();
        List<Book> list = new ArrayList<>();
        Book one = new Book(2, "aaaa", "123");
        Book two = new Book(2, "bbb", "1235");
        list.add(one);
        list.add(two);
        list.add(book);
        product.setProList(list);
        product.setName("shu");
        return product;
    }

}
