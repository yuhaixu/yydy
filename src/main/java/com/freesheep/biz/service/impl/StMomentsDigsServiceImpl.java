package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StMomentsDigsDAO;
import com.freesheep.biz.model.StMomentsDigsBO;
import com.freesheep.biz.service.StMomentsDigsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StMomentsDigsServiceImpl implements StMomentsDigsService {

    @Resource
    StMomentsDigsDAO dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StMomentsDigsBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StMomentsDigsBO record) {
        return 0;
    }

    @Override
    public StMomentsDigsBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StMomentsDigsBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StMomentsDigsBO record) {
        return 0;
    }
}
