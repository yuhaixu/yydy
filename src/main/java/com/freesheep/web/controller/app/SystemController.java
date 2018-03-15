package com.freesheep.web.controller.app;


import com.freesheep.web.controller.BaseSecretController;
import com.freesheep.web.util.DateUtils;
import com.freesheep.web.vo.ResultView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app/system")
public class SystemController extends BaseSecretController {

    @ResponseBody
    @RequestMapping("/time")
    public ResultView getSystemTime(){
        return result(DateUtils.getNowTime());
    }

}
