package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.PermissionGroupDAO;
import com.freesheep.biz.model.PermissionGroupBO;
import com.freesheep.biz.service.PermissionGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Resource
    PermissionGroupDAO groupDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(PermissionGroupBO record) {
        return 0;
    }

    @Override
    public int insertSelective(PermissionGroupBO record) {
        return 0;
    }

    @Override
    public PermissionGroupBO selectByPrimaryKey(Long id) {
        return groupDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionGroupBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PermissionGroupBO record) {
        return 0;
    }
}
