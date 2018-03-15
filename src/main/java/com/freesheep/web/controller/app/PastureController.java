package com.freesheep.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.StPastureBO;
import com.freesheep.biz.service.StPastureService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.PageVO;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/pasture")
public class PastureController extends BaseSecretController {

    @Resource
    StPastureService pastureService;


    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultView getPastureList(){
        Map<String, Object> map = getBodyMap();
        if(map == null){
            return result(null,"请求参数异常");
        }
        String pageStr = Utils.getSomeValue(map.get("page"));
        int page = 1;
        if(!StringUtils.isEmpty(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if(page < 1) page = 1;
        int pageSize = 20;

        Page<StPastureBO> pasturePage = pastureService.getPastureList(getPageRequest(page, pageSize));
        List<StPastureBO> list = pasturePage.getContent();
        long total = pasturePage.getTotalElements();
        int totalPages = pasturePage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("pastureList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);

        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.APP_REQUEST));
    }

}
