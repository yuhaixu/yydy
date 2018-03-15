
package com.freesheep.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.freesheep.common.extend.LogFactory;
import com.freesheep.common.util.AESUtil;
import com.freesheep.common.util.ErrorCode;
import com.freesheep.web.util.Constant;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public abstract class BaseSecretController {
    protected Logger logger = LogFactory.getLogger(BaseAPIController.class);
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected LocaleResolver localeResolver;
    @Resource
    protected AbstractResourceBasedMessageSource messageSource;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Long getDSPId() {
        Object o = request.getAttribute(Constant.REQUEST_ATTRIBUTE_DSPID);
        if (o != null) {
            System.out.println(o);
            return Long.valueOf(String.valueOf(o));
        }
        return null;
    }

    protected ResultView checkError(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            if (errorList != null && errorList.size() > 0) {
                return new ResultView(getLocale(), messageSource, ErrorCode.VALUE_ERROR.getCode(), errorList.get(0).getDefaultMessage());
            }
        }
        return null;
    }

    protected Locale getLocale() {
        return localeResolver.resolveLocale(request);
    }

    protected PageRequest getPageRequest() {
        return getPageRequest("id", Sort.Direction.DESC);
    }

    protected PageRequest getPageRequest(String sortOrder, Sort.Direction direction) {
        String pageString = request.getParameter("page");
        String sizeString = request.getParameter("size");
        int page = 1;
        int size = 10;
        if (StringUtils.isNotBlank(pageString)) {
            page = Integer.parseInt(pageString);
            if (page <= 0)
                page = 1;
        }
        if (StringUtils.isNotBlank(sizeString)) {
            size = Integer.parseInt(sizeString);
            if (size < 0)
                size = 0;
        }
        return new PageRequest(page - 1, size, new Sort(new Sort.Order(sortOrder).with(direction)));
    }

    protected PageRequest getPageRequest(int page, int size) {
        return new PageRequest(page - 1, size);
    }

    @ExceptionHandler
    @ResponseBody
    public ResultView exp(Exception ex) {
        logger.error(this.getClass() + " Exception", ex);
        return new ResultView(getLocale(), messageSource, ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }

    protected String getBodyInfo(){
        String body = null;
        try {
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    protected Map<String, Object> getBodyMap() {
        return getBodyMap(Constant.APP_REQUEST);
    }

    protected Map<String, Object> getBodyMap(String key) {
        String body = null;
        try {
            body = AESUtil.decryptForBase64(input2byte(request.getInputStream()), key);
            System.out.println("==============BaseSecretController  请求body===============");
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> paramMap = null;
        if (StringUtils.isNotBlank(body)) {
            paramMap = JSONObject.parseObject(body, new TypeReference<Map<String, Object>>() {
            });
        }
        return paramMap;
    }

    protected Map<String, Object> testBody() {
        String body = null;
        try {
            body = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("==============BaseSecretController  请求body===============");
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> paramMap = null;
        if (StringUtils.isNotBlank(body)) {
            paramMap = JSONObject.parseObject(body, new TypeReference<Map<String, Object>>() {
            });
        }
        return paramMap;
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

    protected ResultView result(ErrorCode errorCode) {
        return new ResultView(errorCode, getLocale(), messageSource);
    }

    protected ResultView result(String code, String... message) {
        return new ResultView(getLocale(), messageSource, code, message);
    }

    protected ResultView result(Object o) {
        return new ResultView(o);
    }
}
