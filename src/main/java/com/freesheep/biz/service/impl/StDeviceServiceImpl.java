package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StDeviceDAO;
import com.freesheep.biz.model.StDeviceBO;
import com.freesheep.biz.service.StDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StDeviceServiceImpl implements StDeviceService {

    @Resource
    StDeviceDAO deviceDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(StDeviceBO record) {
        return 0;
    }

    @Override
    public long insertSelective(StDeviceBO record) {
        int low = deviceDAO.insertSelective(record);
        if(low < 1) return 0;
        return record.getId();
    }

    @Override
    public StDeviceBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StDeviceBO record) {
        return deviceDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(StDeviceBO record) {
        return 0;
    }

    @Override
    public StDeviceBO selectByDeviceNum(String deviceNo) {
        return deviceDAO.selectByDeviceNum(deviceNo);
    }
}
