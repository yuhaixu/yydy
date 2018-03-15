
package com.freesheep.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.web.util.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SignInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
    @Resource
    protected LocaleResolver localeResolver;
    @Resource
    protected AbstractResourceBasedMessageSource messageSource;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");


        String body = null;
        try {
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
            if(StringUtils.isNotBlank(body)) {
                System.out.println("=========SignInterceptor====body==============");
                System.out.println(body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("=============request uri==============" + request.getRequestURI());

        Map<String, Object> paramMap = null;
        if (StringUtils.isNotBlank(body)) {
            paramMap = JSONObject.parseObject(body, new TypeReference<Map<String, Object>>() {
            });
        }

        String host = request.getRemoteHost();
        String uid = "";
        String token = "";
        if(paramMap != null) {
            uid = Utils.getSomeValue(paramMap.get("uid"));
            token = Utils.getSomeValue(paramMap.get("token"));
        }

        System.out.println("====================验证请求===================");
        System.out.println("host = " + host);
        System.out.println("uid = " + uid);
        System.out.println("token = " + token);
        System.out.println("==============================================");


        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(token) ) {

            return true;
        }
        // 验证没有通过
//        ResultView resultView = new ResultView(ErrorCode.PERMISSION_ERROR, localeResolver.resolveLocale(request), messageSource);
//        WebUtil.writeJson(resultView, response);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
