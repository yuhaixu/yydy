package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.AdminUserBO;
import com.freesheep.biz.model.PermissionGroupBO;
import com.freesheep.biz.service.AdminUserService;
import com.freesheep.biz.service.PermissionGroupService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.web.controller.BaseController;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.Constant;
import com.freesheep.web.util.PermissionUtils;
import com.freesheep.web.util.Utils;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserPermissionController extends BaseSecretController {

    @Resource
    protected AdminUserService adminUserService;
    @Resource
    protected PermissionGroupService groupService;


    @ResponseBody
    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    public ResultView createUser(){
//        Map<String, Object> map = getBodyMap();
        Map<String, Object> map = getBodyMap(Constant.ADMIN_REQUEST);
        if(map == null) return result(null, "参数错误");
        String gidStr = Utils.getSomeValue(map.get("group_id"));
        String loginName = Utils.getSomeValue(map.get("login_name"));
        String pwd = Utils.getSomeValue(map.get("login_pwd"));
        if(StringUtils.isBlank(gidStr) || StringUtils.isBlank(loginName) || StringUtils.isBlank(pwd)){
            return result(null, "参数错误");
        }
        Long gid = Utils.parseLong(gidStr);
        if( gid < 1 ) return result(null, "参数错误");
        PermissionGroupBO groupBO = groupService.selectByPrimaryKey(gid);
        if(groupBO == null) {
            ResultView resultView = result(null, "不存在的权限组");
            resultView.setResult("2");
            return resultView;
        }
        AdminUserBO userBO = new AdminUserBO();
        userBO.setPermissionGroupId(Utils.parseInt(gidStr));
        userBO.setUserName(loginName);
        userBO.setLoginName(loginName);
        try {
            userBO.setLoginPwd(DigestUtils.encodeMD5Hex(pwd.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userBO.setClaimOrder(groupBO.getClaimOrder());
        userBO.setGoodsOrder(groupBO.getGoodsOrder());
        userBO.setDeviceList(groupBO.getDeviceList());
        userBO.setDeviceType(groupBO.getDeviceType());
        userBO.setDeviceStatus(groupBO.getDeviceStatus());
        userBO.setPastureList(groupBO.getPastureList());
        userBO.setSheepBreedsList(groupBO.getSheepBreedsList());
        userBO.setIsSuperAdmin(0);
        Date date = new Date();
        userBO.setCreateTime(date);
        userBO.setModifyTime(date);
        int row = adminUserService.insertSelective(userBO);
        if(row > 0){
            return result("创建用户成功");
        }
        return result(null,"创建用户失败");
    }

    @ResponseBody
    @RequestMapping(value = "/get_user_info", method = RequestMethod.POST)
    public ResultView getUserInfo(){
//        Map<String, Object> map = getBodyMap();
        Map<String, Object> map = getBodyMap(Constant.ADMIN_REQUEST);
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        if(StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        AdminUserBO userBO = adminUserService.selectByUserId(uid);
        if(userBO != null){
            JSONObject json = new JSONObject();
            json.put("user_id", userBO.getId());
            json.put("user_name", userBO.getUserName());
            json.put("group_id", userBO.getPermissionGroupId());
            json.put("group_name", userBO.getGroupName());
            json.put("is_super_admin", userBO.getIsSuperAdmin());
            json.put("permission", PermissionUtils.getPermissionJson(userBO));
//            return result(json);
            return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
        }
        return result(null, "参数错误");
    }

    @ResponseBody
    @RequestMapping(value = "/get_user_list", method = RequestMethod.POST)
    public ResultView getUserList(){
//        Map<String, Object> map = getBodyMap();
        Map<String, Object> map = getBodyMap(Constant.ADMIN_REQUEST);
        if(map == null) return result(null, "参数错误");
        String uidStr = Utils.getSomeValue(map.get("user_id"));
        if(StringUtils.isBlank(uidStr)) return result(null, "参数错误");
        long uid = Utils.parseLong(uidStr);
        if (uid < 1) return result(null, "参数错误");
        AdminUserBO userBO = adminUserService.selectByPrimaryKey(uid);
        if(userBO == null) return result(null, "参数错误");
        if(userBO.getIsSuperAdmin() != 1) return result("");

        String pageStr = Utils.getSomeValue(map.get("page"));
        int page = 1;
        if(StringUtils.isNotBlank(pageStr)) page = Utils.parseInt(pageStr);
        if(page < 1) page = 1;
        int pageSize = 20;

        Page<AdminUserBO> appPage = adminUserService.getAdminUserList(getPageRequest(page, pageSize));
        List<AdminUserBO> list = appPage.getContent();
        long total = appPage.getTotalElements();
        int totalPages = appPage.getTotalPages();
        JSONObject json = new JSONObject();
        json.put("userList", list);
        json.put("total", total);
        json.put("totalPages", totalPages);
        json.put("pageSize", pageSize);
        return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
    }

}
