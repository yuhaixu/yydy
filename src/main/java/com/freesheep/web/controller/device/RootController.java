package com.freesheep.web.controller.device;

import com.freesheep.web.controller.BaseController;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class RootController extends BaseController {

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String echo() {
        System.out.println("root controller");
        String body = null;
        try {
            request.getInputStream();
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("post  " + body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isNoneBlank(body)) {
            try {
                String uri = request.getQueryString();
                if(StringUtils.isNoneBlank(uri)) {
                    body = URLDecoder.decode(request.getQueryString(), "utf-8");
                    System.out.println("get  " + body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isNoneBlank(body)){
            body = "null";
        }
        return body;
    }

}
