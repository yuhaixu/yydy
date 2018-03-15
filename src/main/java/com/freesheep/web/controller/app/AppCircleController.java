package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.model.StMomentsMediaBO;
import com.freesheep.biz.model.StUsersBO;
import com.freesheep.biz.service.StMomentsMediaService;
import com.freesheep.biz.service.StMomentsService;
import com.freesheep.biz.service.StUsersService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/app/sheep_circle")
public class AppCircleController extends BaseSecretController {


    @Resource
    StMomentsService momentsService;
    @Resource
    StUsersService usersService;
    @Resource
    StMomentsMediaService mediaService;


    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResultView publish(){

        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }

        System.out.println(map.toString());

        String userId = Utils.getSomeValue(map.get("user_id"));
        String myType = Utils.getSomeValue(map.get("type"));
        String content = Utils.getSomeValue(map.get("content"));
        String location = Utils.getSomeValue(map.get("location"));
        List<String> mediaList = (List<String>) map.get("media");

        if((mediaList == null || mediaList.size() == 0) || StringUtils.isEmpty(content) || StringUtils.isEmpty(myType)){
            return result(null,"请求参数异常");
        }

        int type = Integer.parseInt(myType);
        if(type > 3 || type < 1) return result(null,"请求参数异常");

        StMomentsBO momentsBO = new StMomentsBO();
        momentsBO.setUserId(Integer.parseInt(userId));
        momentsBO.setContent(content);
        if(!StringUtils.isEmpty(location)) {
            momentsBO.setLocation(location);
        }
        momentsBO.setDig(0);
        momentsBO.setDis(0);
        momentsBO.setShareCount(0L);
        momentsBO.setMtype(type);
        Date now = new Date();
        momentsBO.setCreated(now);
        momentsBO.setModified(now);

        int mid = momentsService.insertMoment(momentsBO);
        if(mid <= 0) {
            return result(null, "发布失败");
        }

        List<StMomentsMediaBO> mediaBOList = new ArrayList<>();
        for(int i = 0; i < mediaList.size(); i++){
            StMomentsMediaBO  mediaBO = new StMomentsMediaBO();
            mediaBO.setMid(mid);
            mediaBO.setUrl(mediaList.get(i));
            mediaBO.setCreated(now);
            mediaBO.setModified(now);
            mediaBOList.add(mediaBO);
        }
        mediaService.insertMediaList(mediaBOList);
        return result("发布成功");
    }


    @ResponseBody
    @RequestMapping(value = "/show_user_circle", method = RequestMethod.POST)
    public ResultView showUserCircle(){

        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }

        System.out.println(map.toString());
        String userId = Utils.getSomeValue(map.get("user_id"));
        String showUserId = Utils.getSomeValue(map.get("show_user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(showUserId)) return result(null,"请求参数异常");

        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return result(null,"请求参数异常");
            }
        }
        int pageSize = 8;

        System.out.println("page = " + page);
        System.out.println("pageStr = " + pageStr);

        int showId = Integer.parseInt(showUserId);
        int uid = Integer.parseInt(userId);

        Page<StMomentsBO> appPage = momentsService.selectMomentIdListById(getPageRequest(page, pageSize), showId);
        List<StMomentsBO> idList = appPage.getContent();
        if(idList == null || idList.size() == 0) return result("");

        List<StMomentsBO> circleList = getMomentsList(idList);
        StUsersBO usersBO = usersService.selectUserForWeb(uid);
        StUsersBO showUsersBO = usersService.selectUserForWeb(showId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("circleList", circleList);
        jsonObject.put("me", usersBO);
        jsonObject.put("user", showUsersBO);
        return result(AESUtil.encryptForBase64(jsonObject.toJSONString(), Constant.APP_REQUEST));

    }


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView circleList(){

        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        String uid = Utils.getSomeValue(map.get("user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));
        System.out.println(map.toString());
        if(StringUtils.isEmpty(uid)) return result(null,"请求参数异常");

        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return result(null,"请求参数异常");
            }
        }

        int pageSize = 8;

        Page<StMomentsBO> appPage = momentsService.selectMomentIdList(getPageRequest(page, pageSize));
        List<StMomentsBO> idList = appPage.getContent();
        if(idList == null || idList.size() == 0) return result("");

        List<StMomentsBO> circleList = getMomentsList(idList);
        StUsersBO usersBO = usersService.selectUserForWeb(Integer.parseInt(uid));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("circleList", circleList);
        jsonObject.put("me", usersBO);
        return result(AESUtil.encryptForBase64(jsonObject.toJSONString(), Constant.APP_REQUEST));
    }

    private List<StMomentsBO> getMomentsList(List<StMomentsBO> idList){
        ArrayList<StMomentsBO> momentsList = new ArrayList<>();
        List<Integer> midList = new ArrayList<>();
        Map<Integer, StMomentsBO> map = new HashedMap();
        for(StMomentsBO bo:idList){
            int id = bo.getId();
            midList.add(id);
            map.put(id, bo);
            momentsList.add(bo);
        }
        List<StMomentsBO> mediaList = momentsService.selectMediaList(midList);
        for(StMomentsBO bo:mediaList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setMedia(bo.getMedia());
            map.put(id, momentsBO);
        }

        List<StMomentsBO> digList = momentsService.selectMomentsDigList(midList);
        for(StMomentsBO bo:digList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setDigs(bo.getDigs());
            map.put(id, momentsBO);
        }

        List<StMomentsBO> disList = momentsService.selectMomentsDisList(midList);
        for(StMomentsBO bo:disList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setMomentList(bo.getMomentList());
            map.put(id, momentsBO);
        }

        for(int i = 0; i < momentsList.size(); i++){
            StMomentsBO bo = momentsList.get(i);
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsList.set(i, momentsBO);
        }
        return momentsList;
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public ResultView test(){
        String pageStr = request.getParameter("page");
        int page = Integer.parseInt(pageStr);
        int pageSize = 8;
        Page<StMomentsBO> appPage = momentsService.selectMomentIdList(getPageRequest(page, pageSize));
        List<StMomentsBO> idList = appPage.getContent();
        if(idList == null || idList.size() == 0) return result("");
        ArrayList<StMomentsBO> momentsList = new ArrayList<>();
        List<Integer> midList = new ArrayList<>();
        Map<Integer, StMomentsBO> map = new HashedMap();
        for(StMomentsBO bo:idList){
            int id = bo.getId();
            midList.add(id);
            map.put(id, bo);
            momentsList.add(bo);
        }
        List<StMomentsBO> mediaList = momentsService.selectMediaList(midList);
        for(StMomentsBO bo:mediaList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setMedia(bo.getMedia());
            map.put(id, momentsBO);
        }

        List<StMomentsBO> digList = momentsService.selectMomentsDigList(midList);
        for(StMomentsBO bo:digList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setDigs(bo.getDigs());
            map.put(id, momentsBO);
        }

        List<StMomentsBO> disList = momentsService.selectMomentsDisList(midList);
        for(StMomentsBO bo:disList){
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            momentsBO.setMomentList(bo.getMomentList());
            map.put(id, momentsBO);
        }

        for(int i = 0; i < momentsList.size(); i++){
            StMomentsBO bo = momentsList.get(i);
            int id = bo.getId();
            StMomentsBO momentsBO = map.get(id);
            System.out.println(momentsBO.toString());
            momentsList.set(i, momentsBO);
        }

        return result(momentsList);
    }


}
