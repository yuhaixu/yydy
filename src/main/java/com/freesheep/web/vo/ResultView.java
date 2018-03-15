package com.freesheep.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freesheep.common.util.ErrorCode;
import com.freesheep.web.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractResourceBasedMessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultView {
    @JsonProperty(value = "error_code")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    private String result;

    private Object data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "message")
    private String err;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultView(Locale locale, AbstractResourceBasedMessageSource resource, Object[] args, String errorCode,
                      String... message) {
        this.errorCode = errorCode;
        if (message == null || message.length == 0) {
            this.message = "error";
        } else {
            List<String> messageList = new ArrayList<String>(message.length);
            for (String m : message) {
                String msg = resource.getMessage(m, args, locale);
                if (StringUtils.isBlank(msg)) {
                    this.message = errorCode;
                } else {
                    messageList.add(msg);
                    this.message = StringUtils.join(messageList, ",");
                }
            }

        }
        System.out.println("message: " + this.message);
        this.result = Constant.FAILURE;
    }

    public ResultView(Locale locale, AbstractResourceBasedMessageSource resource, String errorCode, String... message) {
        this(locale, resource, null, errorCode, message);
    }

    public ResultView(Object data) {
        if (data instanceof ErrorCode) {
            this.result = Constant.FAILURE;
            this.data = data;
            return;
        }
        this.result = Constant.SUCCESS;
        System.out.println(data.toString());
        this.data = data;
    }

    public ResultView(ErrorCode errorCode, Locale locale, AbstractResourceBasedMessageSource resource) {
        this(locale, resource, errorCode.getCode(), errorCode.getMessage());
    }
}
