package com.freesheep.web.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping("/web/wx")
public class WXRequestController extends BaseSecretController {

    @ResponseBody
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public ResultView getAccessToken(HttpServletResponse response){

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");

        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        String appid = Utils.getSomeValue(map.get("appid"));
        String appSecret = Utils.getSomeValue(map.get("app_secret"));
        if(StringUtils.isEmpty(appid) || StringUtils.isEmpty(appSecret)) return result(null,"请求参数异常");
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appSecret;
        String  accessToken = HttpRequest.get(url);
        System.out.println(" getAccessToken   "  + accessToken);
        Map<String, Object> paramMap = parseJson(accessToken);
        if(paramMap == null) return result(null,accessToken);

        String asToken = (String) paramMap.get("access_token");
        String getSignUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ asToken +"&type=jsapi";
        String signResult = HttpRequest.get(getSignUrl);
        System.out.println("signResult = " + signResult);
        Map<String, Object> signMap = parseJson(signResult);
        if(signMap == null) return result(null,signResult);

        JSONObject json = new JSONObject();
        json.put("ticket", signMap.get("ticket"));

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }

    private Map<String, Object> parseJson(String json){
        Map<String, Object> paramMap = null;
        if (StringUtils.isNotBlank(json)) {
            paramMap = JSONObject.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        }
        return paramMap;
    }

}
