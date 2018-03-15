package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.DeviceTestInfoDAO;
import com.freesheep.biz.model.DeviceTestInfoBO;
import com.freesheep.biz.service.DeviceTestInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceTestInfoServiceImpl implements DeviceTestInfoService {

    @Resource
    DeviceTestInfoDAO deviceTestInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(DeviceTestInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(DeviceTestInfoBO record) {
        return deviceTestInfoDAO.insertSelective(record);
    }

    @Override
    public DeviceTestInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(DeviceTestInfoBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DeviceTestInfoBO record) {
        return 0;
    }
}
