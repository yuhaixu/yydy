package com.freesheep.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.vo.ResultView;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestWebController extends BaseController {

    @RequestMapping("request")
    @ResponseBody
    public ResultView requestTest(){
        String url = "http://192.168.1.149:8080/device/echo";
        Map<String, String> map = new HashMap<>();
        map.put("abc","ddd");
        map.put("nnn","fff");
        map.put("rrr","www");
        String respone = HttpRequest.post(url, map);
//        String respone = HttpRequest.get("http://192.168.1.149:8080/device/echo?abcde");
        return result(respone);
    }

    @RequestMapping("web/test")
    @ResponseBody
    public ResultView test(){
        String url = "http://60.205.220.219/freesheeps/?service=User.GetGoods";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("curr",0);
        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(jsonObject, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,entity, String.class);
        String body = responseEntity.getBody();

        if(body != null){
            return result(body);
        }

        return result(null, "web测试接口数据");
    }

    @RequestMapping("web/login")
    @ResponseBody
    public ResultView login(){
        return result(null, "登录接口测试");
    }


}
