package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StFavoriteBO;
import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.service.StFavoriteService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
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
@RequestMapping("/app/collection")
public class CollectionController extends BaseSecretController {


    @Resource
    StFavoriteService favoriteService;

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultView delCollection(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String cidStr = Utils.getSomeValue(map.get("collection_id"));

        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(cidStr)) return result(null, "参数错误");

        int cid = Utils.parseInt(cidStr);
        int uid = Utils.parseInt(uidStr);
        if(cid < 1 || uid <1) return result(null, "参数错误");

        StFavoriteBO bo = favoriteService.selectByPrimaryKey(cid);
        if(bo == null) return result(null, "收藏的商品不存在");
        if(bo.getUserId() != uid) return result(null, "参数错误");

        int row = favoriteService.deleteByPrimaryKey(cid);
        if( row > 0) {
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        }

        return result(null, "删除收藏商品失败");
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultView addCollection(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String pidStr = Utils.getSomeValue(map.get("pr_id"));
        if(StringUtils.isBlank(uidStr) || StringUtils.isBlank(pidStr)) return result(null, "参数错误");

        int pid = Utils.parseInt(pidStr);
        int uid = Utils.parseInt(uidStr);
        if(pid < 1 || uid <1) return result(null, "参数错误");

        long count = favoriteService.selectCollectionCount(uid, pid);
        if(count > 0){
            ResultView resultView = result(null, "已收藏该商品");
            resultView.setResult("2");
            return resultView;
        }

        Date date = new Date();
        StFavoriteBO bo = new StFavoriteBO();
        bo.setPrId(pid);
        bo.setUserId(uid);
        bo.setCreated(date);
        bo.setModified(date);
        int row = favoriteService.insertSelective(bo);
        if(row > 0){
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        }
        return result(null, "收藏商品失败");
    }


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView collectionList(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String pageStr = Utils.getSomeValue(map.get("page"));

        if(StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        if(uid < 1) return result(null, "参数错误");

        int page = 1;
        if(StringUtils.isNotBlank(pageStr)){
            page = Utils.parseInt(pageStr);
        }
        if(page < 1) page = 1;
        int pageSize = 14;

        Page<StFavoriteBO> appPage = favoriteService.selectCollectionList(getPageRequest(page, pageSize), uid);
        List<StFavoriteBO> list = appPage.getContent();
        long total = appPage.getTotalElements();
        int totalPages = appPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("list", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


}
