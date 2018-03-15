package com.freesheep.web.controller.adminServer;

import com.alibaba.fastjson.JSONObject;
import com.freesheep.biz.model.AdminUserBO;
import com.freesheep.biz.service.AdminUserService;
import com.freesheep.common.util.AESUtil;
import com.freesheep.common.util.DigestUtils;
import com.freesheep.common.util.RSAUtils;
import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.*;
import com.freesheep.web.vo.ResultView;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseSecretController {

    @Resource
    AdminUserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultView login(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Credentials", "true");

        Map<String, Object> map = getBodyMap(Constant.ADMIN_REQUEST);
        if(map == null) return result(null, "参数错误");

        String userName = Utils.getSomeValue(map.get("user_name"));
        String password = Utils.getSomeValue(map.get("pwd"));
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            AdminUserBO userBO = userService.selectByUserName(userName);
            if(userBO == null){
                return result(null, "用户名或密码错误");
            }
            try {
                String pwd = DigestUtils.encodeMD5Hex(password.getBytes("utf-8"));
                if(userBO.getLoginPwd().equals(pwd)){
                    String cookieValue = Base64.encodeBase64String(AESUtil.cookEncrypt(userName + "," + System.currentTimeMillis(), Constant.LOGIN_COOKIE_PASSWORD));
                    System.out.println("########cookieValue = " + cookieValue);
                    String domain = Constant.COOKIE_DOMAIN;
                    CookieUtil.addCookie(Constant.DEFAULT_CSRF_COOKIE_NAME, cookieValue, null, 30 * 60, response);
                    String token = RSAUtils.getToken(userBO.getLoginName() + ":" + pwd);
                    JSONObject json = new JSONObject();
                    json.put("user_id", userBO.getId());
                    json.put("token", token);
                    json.put("is_super_admin", userBO.getIsSuperAdmin());
                    json.put("permission", PermissionUtils.getPermissionJson(userBO));
                    logger.info("#login login name=" + userBO.getLoginName() + " ******** " + DateUtils.getNowTime());
                    return result(AESUtil.encryptForBase64(json.toJSONString(), Constant.ADMIN_REQUEST));
//                    return result(json);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return result(null, "用户名或密码错误");
        } else {
            return result(null, "用户名或密码不能为空");
        }
    }

}
