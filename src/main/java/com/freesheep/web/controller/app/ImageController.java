package com.freesheep.web.controller.app;

import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.net.HttpRequest;
import com.freesheep.web.util.Utils;
import com.freesheep.web.util.qiniu.AuthUtils;
import com.freesheep.web.util.qiniu.UrlSafeBase64;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/app")
public class ImageController extends BaseSecretController {

    @ResponseBody
    @RequestMapping(value = "/delete_img", method = RequestMethod.POST)
    public ResultView deleteImg(){
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        System.out.println(map.toString());

        String bucket = Utils.getSomeValue(map.get("bucket"));
        String fileName = Utils.getSomeValue(map.get("file_name"));
        if(StringUtils.isBlank(bucket) || StringUtils.isBlank(fileName)) return result(null,"请求参数异常");
        if(fileName.contains("http")) return result(null,"请求参数异常");

        String entryURI = bucket + ":" + fileName;
        String url = "http://rs.qiniu.com/delete/" + UrlSafeBase64.encodeToString(entryURI);
        String signingStr =  "/delete/" + UrlSafeBase64.encodeToString(entryURI) +"\n";
        AuthUtils auth = AuthUtils.create("koa-_ArUX87E87G2egrKZu2leHNzeni4gU8dCTtd", "5jYAANe8k65TwajjG3hrNCc8b9ljmvq-7CUxINQE");
        String accessToken = auth.sign(signingStr);
        Header[] header = {
                new BasicHeader("Host", "rs.qiniu.com"),
                new BasicHeader("ContentType", "application/x-www-form-urlencoded"),
                new BasicHeader("Authorization", "QBox " + accessToken)
        };
        String response = HttpRequest.post(url, new HashMap<String, String>(), header);
        System.out.println("response = " + response);
        return result(response);
    }


}
