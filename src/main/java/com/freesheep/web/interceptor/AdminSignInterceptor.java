
package com.freesheep.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.biz.model.AdminUserBO;
import com.freesheep.biz.service.AdminUserService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.common.util.Base64Utils;
import com.freesheep.common.util.ErrorCode;
import com.freesheep.common.util.RSAUtils;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.util.WebUtil;
import com.freesheep.web.vo.ResultView;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class AdminSignInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AdminSignInterceptor.class);
    @Resource
    protected LocaleResolver localeResolver;
    @Resource
    protected AbstractResourceBasedMessageSource messageSource;
    @Resource
    protected AdminUserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");


        String body = null;
        try {
            InputStream inputStream = request.getInputStream();
            body = AESUtil.decryptForBase64(input2byte(inputStream), Constant.ADMIN_REQUEST);
            if(StringUtils.isNotBlank(body)) {
                System.out.println("=========AdminSignInterceptor====body==============");
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
            uid = Utils.getSomeValue(paramMap.get("user_id"));
            token = Utils.getSomeValue(paramMap.get("token"));
        }

        System.out.println("====================验证请求===================");
        System.out.println("host = " + host);
        System.out.println("uid = " + uid);
        System.out.println("token = " + token);
        System.out.println("==============================================");

        logger.warn("####admin sign interceptor,host=" + host + ",uid=" + uid + ",token=" + token);
        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(token) ) {
            AdminUserBO userBO = userService.selectByPrimaryKey(Utils.parseLong(uid));
            if(userBO != null) {
                byte[] tokenArr = Base64Utils.decode(token);
                byte[] prde = RSAUtils.decryptByPrivateKey(tokenArr, Constant.PRIVATE_KEY);
                String to = new String(prde, "utf-8");
                String[] toArr = to.split(":");
                System.out.println("token 解析信息 = " + Arrays.toString(toArr));
                if(toArr.length == 2){
                    if(toArr[0].equals(userBO.getLoginName()) && toArr[1].equals(userBO.getLoginPwd())) {
                        logger.info("#校验成功 host=" + host + ",uid=" + uid + ",token=" + token + ",request uri=" + request.getRequestURI());
                        System.out.println("#校验成功 host=" + host + ",uid=" + uid + ",token=" + token);
                        return true;
                    }
                }
            }
            logger.warn("##校验失败 host=" + host + ",uid=" + uid + ",token=" + token + ",request uri=" + request.getRequestURI());
        }
        System.out.println("##校验失败 host=" + host + ",uid=" + uid + ",token=" + token + ",request uri=" + request.getRequestURI());
        logger.warn("####admin sign interceptor permission error " + ",request uri=" + request.getRequestURI());
        ResultView resultView = new ResultView(ErrorCode.PERMISSION_ERROR, localeResolver.resolveLocale(request), messageSource);
        WebUtil.writeJson(resultView, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
