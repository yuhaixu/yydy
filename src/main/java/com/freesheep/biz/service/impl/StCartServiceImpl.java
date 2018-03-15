package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StCartDAO;
import com.freesheep.biz.model.StCartBO;
import com.freesheep.biz.service.StCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StCartServiceImpl implements StCartService {

    @Resource
    StCartDAO cartDAO;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StCartBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StCartBO record) {
        return cartDAO.insertSelective(record);
    }

    @Override
    public StCartBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StCartBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StCartBO record) {
        return 0;
    }
}
