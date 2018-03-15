package com.freesheep.web.controller.app;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class SmsController extends BaseController {

    @RequestMapping(path = "app/send_weaken_device_sms", method = RequestMethod.POST)
    @ResponseBody
    public ResultView sendWeakenSms(){

        Map<String, Object> map = getBodyMap();
        if(map != null) {
//            String phone = request.getParameter("phone");
            String phone = Utils.getSomeValue( map.get("phone"));
            if (StringUtils.isEmpty(phone)) {
                ResultView resultView = result(null, "手机号不能为空");
                resultView.setResult("2");
                return resultView;
            }

            if (phone.length() != 11) {
                ResultView resultView = result(null, "手机号不正确");
                resultView.setResult("3");
                return resultView;
            }

            HashMap<String, Object> result = null;
            CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
            restAPI.init("app.cloopen.com", "8883");
            restAPI.setAccount("aaf98f89524954cc015258efb9fb1eb3", "069cc52ae44242b9a766064e06240f91");
            restAPI.setAppId("8aaf07085c62aa66015c7652325604c4");
            result = restAPI.sendTemplateSMS(phone, "191121", new String[]{"唤醒"});

            System.out.println("SDKTestGetSubAccounts result=" + result);
            if ("000000".equals(result.get("statusCode"))) {
                //正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet) {
                    Object object = data.get(key);
                    System.out.println(key + " = " + object);
                }
                return result("信息发送成功");
            } else {
                //异常返回输出错误码和错误信息
                System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
            }
            return result(null, "发送失败 statusCode = " + result.get("statusCode") + "  " + result.get("statusMsg"));
        }
        return result(null, "请求参数错误");
    }

}
