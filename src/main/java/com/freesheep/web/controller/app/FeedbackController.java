package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.FeedbackBO;
import com.freesheep.biz.service.FeedbackService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/app/feedback")
public class FeedbackController extends BaseSecretController {

    Logger log = Logger.getLogger(FeedbackController.class);

    @Resource
    FeedbackService service;

    @ResponseBody
    @RequestMapping(value = "/send_msg", method = RequestMethod.POST)
    public ResultView upFeedback(){

        try {
            Map<String, Object> map = getBodyMap();
            if(map == null) return result(null, "参数错误");

            String uidStr = Utils.getSomeValue(map.get("user_id"));
            String content = Utils.getSomeValue(map.get("content"));
            String typeStr = Utils.getSomeValue(map.get("type"));

            if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(content) || StringUtils.isBlank(typeStr)) {
                return result(null, "参数错误");
            }

            long uid = Utils.parseLong(uidStr);
            int type = Utils.parseInt(typeStr);

            if(uid < 1 || type < 1) return result(null, "参数错误");

            Date date = new Date();
            FeedbackBO feedback = new FeedbackBO();
            feedback.setUserId(uid);
            feedback.setContent(content);
            feedback.setType(type);
            feedback.setModifyTime(date);
            feedback.setCreateTime(date);

            int low = service.insertSelective(feedback);
            JSONObject json = new JSONObject();
            json.put("status", low > 0?"1":"0");

            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            log.error(e.getMessage());
            log.error(e.getCause());
            return result(null, "insert 参数错误");
        }
    }

}
