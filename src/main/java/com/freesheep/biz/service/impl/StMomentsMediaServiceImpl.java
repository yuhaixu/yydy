package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StMomentsMediaDAO;
import com.freesheep.biz.model.StMomentsMediaBO;
import com.freesheep.biz.service.StMomentsMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StMomentsMediaServiceImpl implements StMomentsMediaService {

    @Resource
    StMomentsMediaDAO dao;


    @Override
    public List<StMomentsMediaBO> selectMediaForMid(int mid) {
        return dao.selectMediaForMid(mid);
    }

    @Override
    public void insertMediaList(List<StMomentsMediaBO> mediaBOList) {
        dao.insertInBatch(mediaBOList);
    }
}
