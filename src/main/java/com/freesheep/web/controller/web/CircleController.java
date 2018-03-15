package com.freesheep.web.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.model.StMomentsDissBO;
import com.freesheep.biz.model.StMomentsMediaBO;
import com.freesheep.biz.model.StUsersBO;
import com.freesheep.biz.service.StMomentsDissService;
import com.freesheep.biz.service.StMomentsMediaService;
import com.freesheep.biz.service.StMomentsService;
import com.freesheep.biz.service.StUsersService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/web/circle")
public class CircleController extends BaseSecretController {

    @Resource
    StMomentsService momentsService;
    @Resource
    StUsersService usersService;
    @Resource
    StMomentsMediaService mediaService;
    @Resource
    StMomentsDissService dissService;

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public ResultView singleCircle(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");

        Map<String, Object> map = getBodyMap();
        System.out.println(map.toString());
        String mid = Utils.getSomeValue(map.get("mid"));
        if (StringUtils.isEmpty(mid)) {
            return result(null, "请求失败");
        }

        int id = 0;
        try {
            id = Integer.parseInt(mid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null, "请求失败");
        }

        StMomentsBO momentsBO = momentsService.selectByPrimaryKey(id);
        if (momentsBO == null) {
            return result(null, "请求失败");
        }

        List<StMomentsMediaBO> mediaList = mediaService.selectMediaForMid(id);
        if (mediaList == null || mediaList.size() == 0) return result(null, "请求失败");
        List<StUsersBO> digs = usersService.selectCircleUserList(id);
        List<StMomentsDissBO> dissList = dissService.selectMomentListForMid(id);


        JSONObject json = new JSONObject();
        json.put("circle_info", momentsBO);
        json.put("media", mediaList);
        json.put("digs", digs);
        json.put("comment", dissList);

        String resultStr = AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST);

        return result(resultStr);
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public ResultView testSingleCircle(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");

        String mid = (String) request.getParameter("mid");
        if (StringUtils.isEmpty(mid)) {
            return result(null, "请求失败");
        }

        int id = 0;
        try {
            id = Integer.parseInt(mid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null, "请求失败");
        }

        StMomentsBO momentsBO = momentsService.selectByPrimaryKey(id);
        if (momentsBO == null) {
            return result(null, "请求失败");
        }

        List<StMomentsMediaBO> mediaList = mediaService.selectMediaForMid(id);
        if (mediaList == null || mediaList.size() == 0) return result(null, "请求失败");

        List<StUsersBO> digs = usersService.selectCircleUserList(id);
        if (digs == null || digs.size() == 0) return result(null, "请求失败");

        List<StMomentsDissBO> dissList = dissService.selectMomentListForMid(id);
        if (dissList == null || dissList.size() == 0) return result(null, "请求失败");


        JSONObject json = new JSONObject();
        json.put("circle_info", momentsBO);
        json.put("media", mediaList);
        json.put("digs", digs);
        json.put("comment", dissList);

        return result(json);
    }


}
