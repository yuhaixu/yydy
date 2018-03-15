package com.freesheep.web.controller;

import com.freesheep.biz.service.StMomentsService;
import com.freesheep.web.vo.ResultView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController extends BaseController {

    @Resource
    StMomentsService service;

    @RequestMapping("app/test")
    @ResponseBody
    public ResultView test(){
        String mid = request.getParameter("mid");
        int id = Integer.parseInt(mid);
        return result(service.selectMomentList(id));
    }

    @RequestMapping("app/login")
    @ResponseBody
    public ResultView login(){
        return result(null, "登录的测试");
    }

}
