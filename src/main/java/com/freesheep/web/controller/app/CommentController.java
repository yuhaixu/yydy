package com.freesheep.web.controller.app;

import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.model.StMomentsDissBO;
import com.freesheep.biz.service.StMomentsDissService;
import com.freesheep.biz.service.StMomentsService;
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
import java.util.Date;
import java.util.Map;


@Controller
public class CommentController extends BaseSecretController {

    @Resource
    StMomentsDissService dissService;
    @Resource
    StMomentsService momentsService;

    @RequestMapping(path = "/app/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResultView comment(){
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        System.out.println(map.toString());
        String mid = Utils.getSomeValue(map.get("mid"));
        String userIdStr = Utils.getSomeValue(map.get("user_id"));
        String content = (String) map.get("content");
        String toUserIdStr = Utils.getSomeValue(map.get("to_user_id"));
        if(StringUtils.isEmpty(mid) || StringUtils.isEmpty(userIdStr) || StringUtils.isEmpty(content) || StringUtils.isEmpty(toUserIdStr)){
            return result(null,"请求参数异常");
        }

        int id = 0;
        int userId = 0;
        int toUserId = 0;
        try {
            id = Integer.parseInt(mid);
            userId = Integer.parseInt(userIdStr);
            toUserId = Integer.parseInt(toUserIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result(null,"请求参数异常");
        }

        StMomentsBO momentsBO = momentsService.selectByPrimaryKey(id);
        if(momentsBO == null){
            ResultView resultView = result(null, "羊友圈信息不存在");
            resultView.setResult("2");
            return resultView;
        }

        StMomentsDissBO dissBO = getDisBean(id, userId,toUserId, content);
        boolean isSuccess = dissService.insertComment(dissBO);
        if(isSuccess){
            momentsBO.setDis(momentsBO.getDis() + 1);
            momentsBO.setModified(new Date());
            boolean isOk = momentsService.updateCommentCount(momentsBO);
            if(isOk) {
                return result(AESUtil.encryptForBase64("评论成功", Constant.APP_REQUEST));
            }
        }
        return result(null,"请求失败");
    }

    private StMomentsDissBO getDisBean(int id, int userId,int toUserId, String content){
        Date now = new Date();
        StMomentsDissBO dissBO = new StMomentsDissBO();
        dissBO.setMid(id);
        dissBO.setUserId(userId);
        dissBO.setFid(toUserId);
        dissBO.setContent(content);
        dissBO.setCreated(now);
        dissBO.setModified(now);
        return dissBO;
    }

}
