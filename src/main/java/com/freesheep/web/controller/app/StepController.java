package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.FictitiousSheepBO;
import com.freesheep.biz.model.StSheepBO;
import com.freesheep.biz.service.FictitiousDeviceInfoService;
import com.freesheep.biz.service.FictitiousSheepService;
import com.freesheep.biz.service.StDeviceInfoService;
import com.freesheep.biz.service.StSheepService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/app/step")
public class StepController extends BaseSecretController {

    @Resource
    StSheepService sheepService;
    @Resource
    StDeviceInfoService deviceInfoService;
    @Resource
    FictitiousSheepService fictitiousSheepService;
    @Resource
    FictitiousDeviceInfoService fictitiousDeviceInfoService;


    @ResponseBody
    @RequestMapping(value = "/fic_sheep", method = RequestMethod.POST)
    public ResultView getFreeStepCount(){
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String date = Utils.getSomeValue(map.get("date"));

//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String date = Utils.getSomeValue(request.getParameter("date"));
        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(date) || StringUtils.isBlank(sheepIdStr)){
            return result(null, "参数错误");
        }
        long uid = Utils.parseLong(uidStr);
        long sid = Utils.parseLong(sheepIdStr);
        if (uid <= 0 || sid <= 0) return result(null, "参数错误");
        String start = DateUtils.monthFirst(date);
        String end = DateUtils.monthLast(date);
        if(StringUtils.isBlank(start) || StringUtils.isBlank(end)){
            return result(null, "参数错误");
        }
        FictitiousSheepBO sheepBO = fictitiousSheepService.selectByPrimaryKey(sid);
        if(sheepBO == null || sheepBO.getUserId() != uid){
            return result(null, "参数错误");
        }
        long nowStep = fictitiousDeviceInfoService.getCountForTime(sheepBO.getDeviceId(), date + " 00:00:00", date + " 23:59:59");
        long monthStep = fictitiousDeviceInfoService.getCountForTime(sheepBO.getDeviceId(), start + " 00:00:00", end + " 23:59:59");
        JSONObject json = new JSONObject();
        json.put("step", nowStep);
        json.put("monthStep", monthStep);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/sheep", method = RequestMethod.POST)
    public ResultView getStepCount(){

        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");

        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String date = Utils.getSomeValue(map.get("date"));

//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String date = Utils.getSomeValue(request.getParameter("date"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(date) || StringUtils.isBlank(sheepIdStr)){
            return result(null, "参数错误");
        }
        Long uid = Utils.parseLong(uidStr);
        Long sid = Utils.parseLong(sheepIdStr);
        if (uid <= 0 || sid <= 0) return result(null, "参数错误");
        String start = DateUtils.monthFirst(date);
        String end = DateUtils.monthLast(date);
        if(StringUtils.isBlank(start) || StringUtils.isBlank(end)){
            return result(null, "参数错误");
        }

        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sid);
        if(sheepBO == null || sheepBO.getUserId() != uid){
            return result(null, "参数错误");
        }

        long nowStep = deviceInfoService.getCountForTime(sheepBO.getDeviceId(), date + " 00:00:00", date + " 23:59:59");
        long monthStep = deviceInfoService.getCountForTime(sheepBO.getDeviceId(), start + " 00:00:00", end + " 23:59:59");
        JSONObject json = new JSONObject();
        json.put("step", nowStep);
        json.put("monthStep", monthStep);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }

}
