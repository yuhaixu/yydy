package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StMomentsDissDAO;
import com.freesheep.biz.model.StMomentsDissBO;
import com.freesheep.biz.service.StMomentsDissService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StMomentsDissServiceImpl implements StMomentsDissService {


    @Resource
    StMomentsDissDAO dao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StMomentsDissBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StMomentsDissBO record) {
        return 0;
    }

    @Override
    public StMomentsDissBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StMomentsDissBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(StMomentsDissBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StMomentsDissBO record) {
        return 0;
    }

    @Override
    public boolean insertComment(StMomentsDissBO dissBO) {
        return dao.insertComment(dissBO);
    }

    @Override
    public List<StMomentsDissBO> selectMomentListForMid(int mid) {
        return dao.selectMomentListForMid(mid);
    }
}
