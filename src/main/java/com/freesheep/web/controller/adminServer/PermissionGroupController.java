package com.freesheep.web.controller.adminServer;

import com.freesheep.biz.service.PermissionGroupService;
import com.freesheep.web.controller.BaseController;
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
@RequestMapping("/admin")
public class PermissionGroupController extends BaseController {


    @Resource
    protected PermissionGroupService groupService;


    @ResponseBody
    @RequestMapping(value = "/create_perm_group", method = RequestMethod.POST)
    public ResultView createGroup(){
        Map<String, Object> map = getBodyMap();
        if(map == null) return result(null, "参数错误");
        String groupName = Utils.getSomeValue(map.get("group_name"));
        if(StringUtils.isBlank(groupName)) return result(null, "参数错误");


        return null;
    }

}
