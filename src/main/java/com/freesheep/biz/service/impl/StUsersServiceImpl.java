package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StUsersDAO;
import com.freesheep.biz.model.StUsersBO;
import com.freesheep.biz.service.StUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StUsersServiceImpl implements StUsersService {

    @Resource
    StUsersDAO dao;

    @Override
    public StUsersBO selectUserForWeb(int id) {
        return dao.selectUserForWeb(id);
    }

    @Override
    public List<StUsersBO> selectCircleUserList(int mid) {
        return dao.selectForCircleDigs(mid);
    }


}
