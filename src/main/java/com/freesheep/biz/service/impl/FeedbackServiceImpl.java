package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.FeedbackDAO;
import com.freesheep.biz.model.FeedbackBO;
import com.freesheep.biz.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    FeedbackDAO dao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(FeedbackBO record) {
        return 0;
    }

    @Override
    public int insertSelective(FeedbackBO record) {
        return dao.insertSelective(record);
    }

    @Override
    public FeedbackBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FeedbackBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(FeedbackBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FeedbackBO record) {
        return 0;
    }
}
