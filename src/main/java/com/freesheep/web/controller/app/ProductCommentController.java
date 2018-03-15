package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.ProductCommentBO;
import com.freesheep.biz.service.ProductCommentService;
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
@RequestMapping("/app/pro_com")
public class ProductCommentController extends BaseSecretController {

    @Resource
    ProductCommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/pro_com_list", method = RequestMethod.POST)
    public ResultView getProductComm() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String proIdStr = Utils.getSomeValue(map.get("pid"));
        String pageStr = Utils.getSomeValue(map.get("page"));
//        String proIdStr = Utils.getSomeValue(request.getParameter("pid"));
//        String pageStr = Utils.getSomeValue(request.getParameter("page"));
        if (StringUtils.isBlank(proIdStr)) {
            return result(null, "参数错误");
        }
        int pid = Utils.parseInt(proIdStr);
        if (pid < 1) return result(null, "参数错误");


        int page = 1;
        if (!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (page < 1) page = 1;
        int pageSize = 20;

        Page<ProductCommentBO> pasturePage = commentService.getCommentList(getPageRequest(page, pageSize), pid);
        List<ProductCommentBO> list = pasturePage.getContent();
        long total = pasturePage.getTotalElements();
        int totalPages = pasturePage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        json.put("commentList", list);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/publishs", method = RequestMethod.POST)
    public ResultView publishProducts() {
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String proIdStr = Utils.getSomeValue(map.get("pids"));
        String content = Utils.getSomeValue(map.get("content"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String proIdStr = Utils.getSomeValue(request.getParameter("pids"));
//        String content = Utils.getSomeValue(request.getParameter("content"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(content) || StringUtils.isBlank(proIdStr)) {
            return result(null, "参数错误");
        }

        long uid = Utils.parseLong(uidStr);
        if (uid < 1) return result(null, "参数错误");
        Date date = new Date();
        String[] pidArr = proIdStr.split(",");
        if (pidArr == null || pidArr.length < 1) return result(null, "参数错误");
        for (int i = 0; i < pidArr.length; i++) {
            int pid = Utils.parseInt(pidArr[i]);
            ProductCommentBO productCommentBO = new ProductCommentBO();
            productCommentBO.setUserId(uid);
            productCommentBO.setProductId(pid);
            productCommentBO.setContent(content);
            productCommentBO.setCreateTime(date);
            productCommentBO.setModifyTime(date);
            commentService.insertSelective(productCommentBO);
        }
        return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
    }


    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResultView publish() {
        Map<String, Object> map = getBodyMap();
        if (map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        String proIdStr = Utils.getSomeValue(map.get("pid"));
        String content = Utils.getSomeValue(map.get("content"));
//        String uidStr = Utils.getSomeValue(request.getParameter("user_id"));
//        String proIdStr = Utils.getSomeValue(request.getParameter("pid"));
//        String content = Utils.getSomeValue(request.getParameter("content"));

        if (StringUtils.isBlank(uidStr) || StringUtils.isBlank(content) || StringUtils.isBlank(proIdStr)) {
            return result(null, "参数错误");
        }

        long uid = Utils.parseLong(uidStr);
        int pid = Utils.parseInt(proIdStr);
        if (uid < 1 || pid < 1) return result(null, "参数错误");
        Date date = new Date();
        ProductCommentBO productCommentBO = new ProductCommentBO();
        productCommentBO.setUserId(uid);
        productCommentBO.setProductId(pid);
        productCommentBO.setContent(content);
        productCommentBO.setCreateTime(date);
        productCommentBO.setModifyTime(date);
        int row = commentService.insertSelective(productCommentBO);
        if (row > 0) {
            return result(AESUtil.encryptForBase64(Utils.getSuccessJson().toJSONString(), Constant.APP_REQUEST));
        } else {
            return result(null, "评论失败");
        }
    }

}
