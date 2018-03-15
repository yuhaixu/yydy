package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.IosUpdateDAO;
import com.freesheep.biz.model.IosUpdateBO;
import com.freesheep.biz.service.IosUpdateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IosUpdateServiceImpl implements IosUpdateService {

    @Resource
    IosUpdateDAO dao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(IosUpdateBO record) {
        return 0;
    }

    @Override
    public int insertSelective(IosUpdateBO record) {
        return 0;
    }

    @Override
    public IosUpdateBO selectByPrimaryKey(Long id) {
        return dao.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(IosUpdateBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(IosUpdateBO record) {
        return 0;
    }
}
