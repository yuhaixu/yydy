package com.freesheep.web.controller.aliPay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.freesheep.biz.model.ProductCouponBO;
import com.freesheep.biz.model.StClaimOrderBO;
import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.biz.service.ProductCouponService;
import com.freesheep.biz.service.StClaimOrderService;
import com.freesheep.biz.service.StOrdersService;
import com.freesheep.common.extend.AbstractBaseDao;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.config.AliPayConfig;
import com.freesheep.web.config.NewAliPayConfig;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/new_alipay")
public class NewAliPayController extends BaseController implements NewAliPayConfig {

    @Resource
    StClaimOrderService claimOrderService;
    @Resource
    StOrdersService ordersService;
    @Resource
    ProductCouponService couponService;
    Logger log = Logger.getLogger(NewAliPayController.class);


    @ResponseBody
    @RequestMapping(value = "/alipay_notify", method = RequestMethod.POST)
    public String aliPayNotify() {
        String body = null;
        try {
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "failure";
        }
        if (StringUtils.isNotBlank(body)) {
            String[] paramArr = body.split("&");
            if (paramArr == null || paramArr.length == 0) return "failure";
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < paramArr.length; i++) {
                try {
                    String item = paramArr[i];
                    String[] itemArr = item.split("=");
                    String key = itemArr[0];
                    String value = itemArr[1];
                    value = URLDecoder.decode(value, CHARSET);
                    log.info("new_alipay alipay_notify return param key = " + key + "  value = " + value);
                    map.put(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "failure";
                }
            }
            //商户订单号
            String outTradeNo = map.get("out_trade_no");
            //支付宝交易号
            String tradeNo = map.get("trade_no");
            //交易状态
            String tradeStatus = map.get("trade_status");
            try {
                boolean signVerified = AlipaySignature.rsaCheckV1(map, ALIPAY_PUBLIC_KEY, CHARSET, SIGNTYPE);
                if (signVerified) {
                    // 验签成功
                    if("TRADE_SUCCESS".equals(tradeStatus)){
                        payOrder(outTradeNo);
                        // 交易成功
                        log.info("new_alipay alipay_notify 订单号 = " + outTradeNo);
                    }
                    System.out.println("验签成功");
                    return "success";
                } else {
                    System.out.println("验签失败");
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
                return "failure";
            }
        } else {
            return "failure";
        }
        return "failure";
    }

    private void payOrder(String orderNum){
        if(StringUtils.isBlank(orderNum)) return;
        Date date = new Date();
        if(orderNum.contains("ip")){
            // ios 购买商品支付
            String[] arr = orderNum.split("ip");
            if(arr == null || arr.length != 2) return;
            int id = Utils.parseInt(arr[1]);
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

        } else if(orderNum.contains("ap")){
            // android 购买商品支付
            String[] arr = orderNum.split("ap");
            if(arr == null || arr.length != 2) return;
            int id = Utils.parseInt(arr[1]);
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


    @ResponseBody
    @RequestMapping(value = "/return_url", method = RequestMethod.GET)
    public ResultView aliPayReturn(HttpServletResponse response) {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        if(requestParams == null) return result(null, "订单异常");
        System.out.println("同步参数如下：");
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return result(null, "订单异常");
            }
            params.put(name, valueStr);
        }
        try {
            boolean verify_result = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGNTYPE);
            System.out.println(params);
            if(verify_result) {
                // 验签成功
                String orderNum = params.get("out_trade_no");
                String tradeNo = params.get("trade_no");
                String payPrice = params.get("total_amount");
                log.info("return_url 订单号 = " + orderNum);
                if(orderNum.contains("cou")){
                    String[] arr = orderNum.split("cou");
                    if( arr != null && arr.length == 2) {
                        String url = "http://www.freesheeps.com/sheeps/favourable.html?pid=" + arr[0] + "&oid=" + arr[1];
                        log.info("new_alipay 跳转的URL = " + url);
                        response.sendRedirect(url);
                    }
                } else {
                    StClaimOrderBO orderBO = claimOrderService.selectByOrderNum(orderNum);
                    long oid = orderBO.getId();
                    long sheepId = orderBO.getSheepId();
                    JSONObject json = new JSONObject();
                    json.put("sheep_id", sheepId);
                    json.put("order_id", oid);
                    json.put("order_num", orderNum);
                    json.put("pay_price", payPrice);
                    json.put("serial_num", tradeNo);
                    String data = AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST);
                    response.sendRedirect(REDIRECT_URL + "?data=" + data);
                }
            } else {
                return result(null, "订单异常");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return result(null, "订单异常");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return result(null, "订单异常");
        }
        return result("");
    }

}
