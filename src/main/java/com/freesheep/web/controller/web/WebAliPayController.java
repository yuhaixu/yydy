package com.freesheep.web.controller.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.freesheep.web.config.AliPayConfig;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.protral.helper.BodyReaderHttpServletRequestWrapper;
import com.freesheep.web.protral.helper.HttpHelper;
import com.freesheep.web.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web/ali_pay")
public class WebAliPayController extends BaseSecretController implements AliPayConfig {


    @RequestMapping(value = "/put_coupon_order", method = RequestMethod.POST)
    public void putCouponOrder(HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");

        ServletRequest requestWrapper = null;
        try {
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = HttpHelper.getBodyString(requestWrapper);
        System.out.println(body);
        if(StringUtils.isEmpty(body)) {
            writeData(httpResponse, "参数异常");
            return;
        }
        String[] paramArr = body.split("&");
        if (paramArr == null || paramArr.length == 0){
            writeData(httpResponse, "参数异常");
            return;
        }
        Map<String, String> map = new HashMap<>();
        System.out.println("body = " + body);
        for (int i = 0; i < paramArr.length; i++) {
            try {
                String item = paramArr[i];
                String[] itemArr = item.split("=");
                String key = itemArr[0];
                String value = itemArr[1];
                value = URLDecoder.decode(value, CHARSET);
                map.put(key, value);
                System.out.println("key = " + key + "   value = " + value);
            } catch (Exception e) {
                e.printStackTrace();
                writeData(httpResponse, "参数异常");
                return ;
            }
        }
        String orderNum = Utils.getSomeValue(map.get("order_num"));
        String totalAmount = Utils.getSomeValue(map.get("total_amount"));
        String goodsName = Utils.getSomeValue(map.get("goods_name"));
        String pid = Utils.getSomeValue(map.get("pid"));
        System.out.println("orderNum = " + orderNum + "  totalAmount = " + totalAmount + "   goodsName = " + goodsName + "  pid = "  + pid);
        if(StringUtils.isEmpty(orderNum) || StringUtils.isEmpty(totalAmount) || StringUtils.isEmpty(goodsName)) {
            writeData(httpResponse, "参数异常");
            return;
        }
        if(StringUtils.isEmpty(pid)){
            pid = "0";
        }
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APPID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGNTYPE); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request

        // get
        alipayRequest.setReturnUrl(RETURN_URL);
        // post
        alipayRequest.setNotifyUrl(NOTIFY_URL);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + (pid + "cou" + orderNum) + "\"," +
                "\"total_amount\":\"" + totalAmount + "\"," +
                "\"subject\":\"" + goodsName + "\"," +
                "\"timeout_express\":\"" + "20m" + "\"," +
                "\"product_code\":\"QUICK_WAP_PAY\"" +
                "}");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        writeData(httpResponse, form);
    }



    @RequestMapping(value = "/put_ali_order", method = RequestMethod.POST)
    public void putOrder(HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");

        ServletRequest requestWrapper = null;
        try {
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = HttpHelper.getBodyString(requestWrapper);
        System.out.println(body);
        if(StringUtils.isEmpty(body)) {
            writeData(httpResponse, "参数异常");
            return;
        }
        String[] paramArr = body.split("&");
        if (paramArr == null || paramArr.length == 0){
            writeData(httpResponse, "参数异常");
            return;
        }
        Map<String, String> map = new HashMap<>();
        System.out.println("body = " + body);
        for (int i = 0; i < paramArr.length; i++) {
            try {
                String item = paramArr[i];
                String[] itemArr = item.split("=");
                String key = itemArr[0];
                String value = itemArr[1];
                value = URLDecoder.decode(value, CHARSET);
                map.put(key, value);
                System.out.println("key = " + key + "   value = " + value);
            } catch (Exception e) {
                e.printStackTrace();
                writeData(httpResponse, "参数异常");
                return ;
            }
        }
        String orderNum = Utils.getSomeValue(map.get("order_num"));
        String totalAmount = Utils.getSomeValue(map.get("total_amount"));
        String goodsName = Utils.getSomeValue(map.get("goods_name"));
        System.out.println("orderNum = " + orderNum + "  totalAmount = " + "   goodsName = " + goodsName);
        if(StringUtils.isEmpty(orderNum) || StringUtils.isEmpty(totalAmount) || StringUtils.isEmpty(goodsName)) {
            writeData(httpResponse, "参数异常");
            return;
        }
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APPID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGNTYPE); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request

        // get
        alipayRequest.setReturnUrl(RETURN_URL);
        // post
        alipayRequest.setNotifyUrl(NOTIFY_URL);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNum + "\"," +
                "\"total_amount\":\"" + totalAmount + "\"," +
                "\"subject\":\"" + goodsName + "\"," +
                "\"timeout_express\":\"" + "20m" + "\"," +
                "\"product_code\":\"QUICK_WAP_PAY\"" +
                "}");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        writeData(httpResponse, form);
    }

    private void writeData(HttpServletResponse httpResponse, String data){
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        try {
            httpResponse.getWriter().write(data);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
