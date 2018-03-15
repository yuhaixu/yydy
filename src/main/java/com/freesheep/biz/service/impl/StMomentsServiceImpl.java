package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StMomentsDAO;
import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.biz.service.StMomentsService;
import com.freesheep.biz.service.StUsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StMomentsServiceImpl implements StMomentsService {

    @Resource
    StMomentsDAO dao;

    @Override
    public StMomentsBO selectByPrimaryKey(Integer id) {
        return dao.getMoment(id);
    }

    @Override
    public boolean updateShareCount(StMomentsBO momentsBO) {
        return dao.updateShareCount(momentsBO);
    }

    @Override
    public boolean updateCommentCount(StMomentsBO momentsBO) {
        return dao.updateCommentCount(momentsBO);
    }

    @Override
    public List<StMomentsBO> selectMomentList(int mid) {
        return dao.selectMomentList(mid);
    }

    @Override
    public Page<StMomentsBO> selectMomentIdList(Pageable pageable) {
        return dao.selectMomentIdList(pageable);
    }

    @Override
    public Page<StMomentsBO> selectMomentIdListById(Pageable pageable, int uid) {
        return dao.selectMomentIdListById(pageable, uid);
    }

    @Override
    public int insertMoment(StMomentsBO momentsBO) {
        int row =  dao.insertSelective(momentsBO);
        if(row <= 0) return -1;
        return momentsBO.getId();
    }

    @Override
    public List<StMomentsBO> selectMediaList(List<Integer> mediaIdList) {
        return dao.selectMediaList(mediaIdList);
    }

    @Override
    public List<StMomentsBO> selectMomentsDigList(List<Integer> mediaIdList) {
        return dao.selectMomentsDigList(mediaIdList);
    }

    @Override
    public List<StMomentsBO> selectMomentsDisList(List<Integer> mediaIdList) {
        return dao.selectMomentsDisList(mediaIdList);
    }
}
