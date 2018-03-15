package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.FictitiousDeviceDAO;
import com.freesheep.biz.model.FictitiousDeviceBO;
import com.freesheep.biz.service.FictitiousDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FictitiousDeviceServiceImpl implements FictitiousDeviceService {

    @Resource
    FictitiousDeviceDAO deviceDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(FictitiousDeviceBO record) {
        return 0;
    }

    @Override
    public int insertSelective(FictitiousDeviceBO record) {
        return 0;
    }

    @Override
    public FictitiousDeviceBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FictitiousDeviceBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FictitiousDeviceBO record) {
        return 0;
    }
}
