package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.biz.model.StSheepBO;
import com.freesheep.biz.service.StDeviceInfoService;
import com.freesheep.biz.service.StSheepService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/app/sheep")
public class SheepController extends BaseSecretController {

    @Resource
    StSheepService sheepService;
    @Resource
    StDeviceInfoService deviceInfoService;

    @ResponseBody
    @RequestMapping(value = "/history_trajectory", method = RequestMethod.POST)
    public ResultView history(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String date = Utils.getSomeValue(map.get("date"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String deviceIdStr = Utils.getSomeValue(map.get("device_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

//        String date = request.getParameter("date");
//        String uidStr = request.getParameter("user_id");
//        String deviceIdStr = request.getParameter("device_id");
//        String sheepIdStr = request.getParameter("sheep_id");

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(deviceIdStr) || StringUtils.isBlank(sheepIdStr)){
            return result(null, "参数错误");
        }

        long deviceId = Utils.parseLong(deviceIdStr);
        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);

        if(StringUtils.isBlank(date)){
            date = DateUtils.getNowDate();
        }
        StSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if(sheepBO.getSheepStatus() != 1) return result(null, "参数错误");
        if(sheepBO.getUserId() != uid) return result(null, "参数错误");
        if(sheepBO.getDeviceId() != deviceId) return result(null, "参数错误");

        String start = date + " 00:00:00";
        String end = date + " 23:59:59";
        List<StDeviceInfoBO> list = deviceInfoService.getHistoryInfo(deviceId, start, end);
        JSONObject json = new JSONObject();
        json.put("history", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }



    @ResponseBody
    @RequestMapping(value = "/sheep_is_claim", method = RequestMethod.POST)
    public ResultView sheepIsClaim(){
        // 该羊是否已被认领
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        if(StringUtils.isEmpty(sheepIdStr)) return result(null, "参数错误");

        long sheepId = Utils.parseLong(sheepIdStr);
        if(sheepId < 0) return result(null, "参数错误");
        StSheepBO bo = sheepService.selectByPrimaryKey(sheepId);
        String status = String.valueOf(bo.getSheepStatus());
        if(bo.getDeviceId() == null || bo.getDeviceId() <= 0){
            status = "-1";
        }
        JSONObject json = new JSONObject();
        json.put("status", status);
        System.out.println("sheep_is_claim 返回值 = " + json.toJSONString());
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/modify_sheep_info", method = RequestMethod.POST)
    public ResultView modifySheepInfo(){
        // 修改羊的信息接口
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String userIdStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String nickName = Utils.getSomeValue(map.get("nickname"));
        String head = Utils.getSomeValue(map.get("head"));
        if(StringUtils.isEmpty(userIdStr) || StringUtils.isEmpty(sheepIdStr)) return result(null, "参数错误");
        long userId = Utils.parseLong(userIdStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        if(userId < 1 || sheepId < 1) return result(null, "参数错误");

        StSheepBO bo = sheepService.selectByPrimaryKey(sheepId);
        if(userId != bo.getUserId()) return result(null, "数据异常");

        if(StringUtils.isBlank(nickName) && StringUtils.isBlank(head)) {
            return result(AESUtil.encryptForBase64("修改成功",  Constant.APP_REQUEST));
        }
        bo.setId(sheepId);
        if(StringUtils.isNotBlank(nickName)) bo.setSheepNickname(nickName);
        if(StringUtils.isNotBlank(head)) bo.setSheepImg(head);
        bo.setModifyTime(new Date());
        int row =  sheepService.updateByPrimaryKeySelective(bo);
        if(row >= 1){
            return result(AESUtil.encryptForBase64("修改成功",  Constant.APP_REQUEST));
        } else {
            return result(AESUtil.encryptForBase64("修改失败",  Constant.APP_REQUEST));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/user_sheep_info", method = RequestMethod.POST)
    public ResultView getUserSheepInfo(){
        // 获取用户下单条羊的信息
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String userIdStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

        if(StringUtils.isEmpty(userIdStr) || StringUtils.isEmpty(sheepIdStr)) return result(null, "参数错误");

        long userId = Utils.parseLong(userIdStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        if(userId < 1 || sheepId < 1) return result(null, "参数错误");

        StSheepBO sheepBO = sheepService.selectUserSheepInfo(userId, sheepId);
        if(sheepBO == null){
            ResultView resultView = result(null, "信息不存在，或没有领取该羊");
            resultView.setResult("2");
            return resultView;
        }
        long deviceId = sheepBO.getDeviceId();
        long stepCount = deviceInfoService.getStepCount(deviceId);
        JSONObject json = new JSONObject();
        json.put("sheep", sheepBO);
        json.put("stepCount", stepCount);
        return result(AESUtil.encryptForBase64(json.toJSONString(),  Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/sheep_user_list")
    public ResultView getSheepUserList(){
        // 获取用户下所有的羊的信息
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");

        String pageStr = Utils.getSomeValue(map.get("page"));
        String userIdStr = Utils.getSomeValue(map.get("user_id"));

//        String pageStr = Utils.getSomeValue(request.getParameter("page"));
//        String userIdStr = Utils.getSomeValue(request.getParameter("user_id"));
        if(StringUtils.isEmpty(userIdStr)) return result(null, "参数异常");
        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            page = Utils.parseInt(pageStr);
        }
        int uid = Utils.parseInt(userIdStr);
        if(uid <= 0) return result(null, "参数异常");
        if(page < 1) page = 1;
        int pageSize = 20;

        Page<StSheepBO> sheepPage = sheepService.selectSheepUserList(getPageRequest(page, pageSize), uid);
        List<StSheepBO> list = sheepPage.getContent();
        long total = sheepPage.getTotalElements();
        int totalPages = sheepPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("sheepList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/sheep_list", method = RequestMethod.POST)
    public ResultView getSheepList(){
        // 获取当前可认领的羊
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");

        String pageStr = Utils.getSomeValue(map.get("page"));
//        String pageStr = request.getParameter("page");
        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            page = Utils.parseInt(pageStr);
        }
        if(page < 1) page = 1;
        int pageSize = 20;

        Page<StSheepBO> sheepPage = sheepService.selectSheepList(getPageRequest(page, pageSize));
        List<StSheepBO> list = sheepPage.getContent();
        long total = sheepPage.getTotalElements();
        int totalPages = sheepPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("sheepList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }



    @ResponseBody
    @RequestMapping(value = "/is_claim", method = RequestMethod.POST)
    public ResultView isClaim(){
        // 用户是否已经认领了羊
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String userIdStr = Utils.getSomeValue(map.get("user_id"));
        if(StringUtils.isEmpty(userIdStr)) return result(null, "参数错误");

        long userId = 0;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        long count = sheepService.sheepCountByUid(userId);

        JSONObject json = new JSONObject();
        json.put("is_claim",count > 0?"1":"0");
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }

}
