package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.AdminUserDAO;
import com.freesheep.biz.model.AdminUserBO;
import com.freesheep.biz.service.AdminUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    AdminUserDAO userDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(AdminUserBO record) {
        return 0;
    }

    @Override
    public int insertSelective(AdminUserBO record) {
        return userDAO.insertSelective(record);
    }

    @Override
    public AdminUserBO selectByPrimaryKey(Long id) {
        return userDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AdminUserBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AdminUserBO record) {
        return 0;
    }

    @Override
    public AdminUserBO selectByUserName(String userName) {
        return userDAO.selectByUserName(userName);
    }

    @Override
    public Page<AdminUserBO> getAdminUserList(Pageable pageable) {
        return userDAO.getAdminUserList(pageable);
    }

    @Override
    public AdminUserBO selectByUserId(Long id) {
        return userDAO.selectByUserId(id);
    }
}
