package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StDeviceInfoDAO;
import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.biz.service.StDeviceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StDeviceInfoServiceImpl implements StDeviceInfoService {

    @Resource
    StDeviceInfoDAO deviceInfoDAO;

    @Override
    public int insertSelective(StDeviceInfoBO bo) {
        return deviceInfoDAO.insertSelective(bo);
    }

    @Override
    public long getStepCount(long deviceId) {
        return deviceInfoDAO.getStepCount(deviceId);
    }

    @Override
    public List<StDeviceInfoBO> getHistoryInfo(long deviceId, String startTime, String endTime) {
        return deviceInfoDAO.getHistoryInfo(deviceId, startTime, endTime);
    }

    @Override
    public long getCountForTime(long deviceId, String start, String end) {
        return deviceInfoDAO.getCountForTime(deviceId, start, end);
    }
}
