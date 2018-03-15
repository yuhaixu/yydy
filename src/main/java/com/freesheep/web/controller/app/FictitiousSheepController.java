package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.FictitiousDeviceInfoBO;
import com.freesheep.biz.model.FictitiousSheepBO;
import com.freesheep.biz.service.FictitiousDeviceInfoService;
import com.freesheep.biz.service.FictitiousSheepService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.manager.WorkManager;
import com.freesheep.web.task.UnlockFictitiousSheepTask;
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
@RequestMapping("/app/fic_sheep")
public class FictitiousSheepController extends BaseSecretController {

    @Resource
    FictitiousSheepService sheepService;
    @Resource
    FictitiousDeviceInfoService deviceInfoService;


    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResultView modifySheepInfo(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String nickName = Utils.getSomeValue(map.get("nickname"));
        String url = Utils.getSomeValue(map.get("sheep_img"));

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(sheepIdStr)) {
            return result(null, "参数错误");
        }
        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        if(uid < 1 || sheepId < 1) return result(null, "参数错误");
        FictitiousSheepBO bo = sheepService.selectByPrimaryKey(sheepId);
        if(bo == null) return result(null, "信息不存在");
        if(bo.getUserId() != uid) return result(null, "参数错误");

        if(StringUtils.isBlank(nickName) && StringUtils.isBlank(url)){
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        }
        Date date = new Date();
        if(StringUtils.isNotBlank(nickName)) {
            bo.setSheepNickname(nickName);
        }
        if(StringUtils.isNotBlank(url)) {
            bo.setSheepImg(url);
        }
        bo.setId(sheepId);
        bo.setModifyTime(date);
        int low = sheepService.updateByPrimaryKeySelective(bo);
        if(low > 0) {
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        }
        return result(null, "信息修改失败");
    }


    @ResponseBody
    @RequestMapping(value = "/sheep_info", method = RequestMethod.POST)
    public ResultView getSheepInfo(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(sheepIdStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        if(uid < 1 || sheepId < 1) return result(null, "参数错误");
        FictitiousSheepBO bo = sheepService.selectSheepInfo(sheepId);
        if(bo == null) return result(null, "暂无当前羊的信息");
        if(bo.getUserId() != uid) return result(null, "获取信息失败");
        JSONObject json = new JSONObject();
        json.put("sheep_info", bo);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/history_trajectory", method = RequestMethod.POST)
    public ResultView history(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String date = Utils.getSomeValue(map.get("date"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String deviceIdStr = Utils.getSomeValue(map.get("device_id"));
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(deviceIdStr) || StringUtils.isBlank(sheepIdStr)){
            return result(null, "参数错误");
        }

        long deviceId = Utils.parseLong(deviceIdStr);
        long uid = Utils.parseLong(uidStr);
        long sheepId = Utils.parseLong(sheepIdStr);
        if(deviceId < 1 || uid < 1 || sheepId < 1) return result(null, "参数错误");

        if(StringUtils.isBlank(date)){
            date = DateUtils.getNowDate();
        }
        FictitiousSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if(sheepBO == null) return result(null, "信息不存在");
        if(!(sheepBO.getUserId() == uid && sheepBO.getSheepStatus() ==1 && sheepBO.getDeviceId() == deviceId)){
            return result(null, "状态异常");
        }

        String start = date + " 00:00:00";
        String end = date + " 23:59:59";
        List<FictitiousDeviceInfoBO> list = deviceInfoService.getHistoryInfo(deviceId, start, end);
        JSONObject json = new JSONObject();
        json.put("history", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/claim_list", method = RequestMethod.POST)
    public ResultView claimList(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));

        if (StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        if (uid <= 0) return result(null, "参数错误");
        List<FictitiousSheepBO> list = sheepService.selectClaimList(uid);
        JSONObject json = new JSONObject();
        json.put("claim_list", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/sheep_is_claim", method = RequestMethod.POST)
    public ResultView sheepIsClaim(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long sheepId = Utils.parseLong(sheepIdStr);
        long uid = Utils.parseLong(uidStr);
        if (sheepId <= 0 || uid <= 0) return result(null, "参数错误");

        long count = sheepService.sheepCountByUid(uid);
        if(count > 0 ) return result(null, "已经免费领取");

        FictitiousSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if(sheepBO == null) return result(null, "信息不存在");

        JSONObject json = new JSONObject();
        if(sheepBO.getUserId() <= 0 && sheepBO.getSheepStatus() == 0){
            json.put("status","1");
        } else {
            json.put("status","0");
        }

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/fictitious_claim", method = RequestMethod.POST)
    public ResultView claimSheep(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));

        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long sheepId = Utils.parseLong(sheepIdStr);
        long uid = Utils.parseLong(uidStr);
        if (sheepId <= 0 || uid <= 0) return result(null, "参数错误");

        long count = sheepService.sheepCountByUid(uid);
        if(count > 0 ) return result(null, "已经免费领取");

        FictitiousSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if(sheepBO == null) return result(null, "信息不存在");
        if (sheepBO.getUserId() > 0 || sheepBO.getSheepStatus() != 2) {
            return result(null, "当前状态不可领取");
        }
        if(sheepBO.getOperateUid() != uid){
            return result(null, "当前状态不可领取");
        }

        FictitiousSheepBO update = new FictitiousSheepBO();
        Date date = new Date();
        update.setModifyTime(date);
        update.setClaimTime(date);
        update.setUserId(uid);
        update.setSheepStatus(1);
        update.setId(sheepId);
        int row = sheepService.updateByPrimaryKeySelective(update);
        if(row > 0){
            // 将任务移除队列
            String name = "fictitious_" + uidStr + "_" + sheepIdStr;
            WorkManager.removeTask(name, uidStr, sheepId);
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        }
        return result(null, "认领失败");
    }


    @ResponseBody
    @RequestMapping(value = "/confirm_sheep", method = RequestMethod.POST)
    public ResultView confirmSheep(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String sheepIdStr = Utils.getSomeValue(map.get("sheep_id"));
        String uidStr = Utils.getSomeValue(map.get("user_id"));
//        String sheepIdStr = Utils.getSomeValue(request.getParameter("sheep_id"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));

        if (StringUtils.isBlank(sheepIdStr) || StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long sheepId = Utils.parseLong(sheepIdStr);
        long uid = Utils.parseLong(uidStr);
        if (sheepId <= 0 || uid <= 0) return result(null, "参数错误");

        long count = sheepService.sheepCountByUid(uid);
        if(count > 0 ) return result(null, "已经免费领取");

        FictitiousSheepBO sheepBO = sheepService.selectByPrimaryKey(sheepId);
        if(sheepBO == null) return result(null, "信息不存在");

        if (sheepBO.getUserId() > 0 && sheepBO.getUserId() > 0) {
            return result(null, "羊的当前状态不可领取");
        } else if(sheepBO.getUserId() <= 0 || sheepBO.getUserId() < 1){
            if (sheepBO.getSheepStatus() == 1) {
                return result(null, "羊的当前状态不可领取");
            } else if(sheepBO.getSheepStatus() == 2 && sheepBO.getOperateUid() == uid){
                return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
            } else if(sheepBO.getSheepStatus() == 2 && sheepBO.getOperateUid() != uid){
                return result(null, "羊的当前状态不可领取");
            }
        }

        int row = sheepService.lockSheepById(sheepId, uid);
        if (row > 0) {
            String name = "fictitious_" + uidStr + "_" + sheepIdStr;
            WorkManager.unlock(name, uidStr, sheepId, UnlockFictitiousSheepTask.class);
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
//            return result(Utils.getSuccessJson());
        } else {
            return result(null, "请求失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/is_claim", method = RequestMethod.POST)
    public ResultView isClaim(){
        // 用户是否已经认领了羊
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        if(StringUtils.isEmpty(uidStr)) return result(null, "参数错误");

        long userId = 0;
        try {
            userId = Long.parseLong(uidStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null, "参数错误");
        }
        long count = sheepService.sheepCountByUid(userId);

        JSONObject json = new JSONObject();
        json.put("is_claim",count > 0?"1":"0");
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView getSheepList(){
        // 获取当前可认领的羊
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");

        String pageStr = Utils.getSomeValue(map.get("page"));
        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            page = Utils.parseInt(pageStr);
        }
        if(page < 1) page = 1;
        int pageSize = 20;

        Page<FictitiousSheepBO> sheepPage = sheepService.selectSheepList(getPageRequest(page, pageSize));
        List<FictitiousSheepBO> list = sheepPage.getContent();
        long total = sheepPage.getTotalElements();
        int totalPages = sheepPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("sheepList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
//        return result(json);
    }

}


