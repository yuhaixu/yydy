package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.FictitiousDeviceInfoDAO;
import com.freesheep.biz.model.FictitiousDeviceInfoBO;
import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.biz.service.FictitiousDeviceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FictitiousDeviceInfoServiceImpl implements FictitiousDeviceInfoService {

    @Resource
    FictitiousDeviceInfoDAO deviceInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(FictitiousDeviceInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(FictitiousDeviceInfoBO record) {
        return 0;
    }

    @Override
    public FictitiousDeviceInfoBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FictitiousDeviceInfoBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FictitiousDeviceInfoBO record) {
        return 0;
    }

    @Override
    public List<FictitiousDeviceInfoBO> getHistoryInfo(long deviceId, String startTime, String endTime) {
        return deviceInfoDAO.getHistoryInfo(deviceId, startTime, endTime);
    }

    @Override
    public long getCountForTime(long deviceId, String start, String end) {
        return deviceInfoDAO.getCountForTime(deviceId, start, end);
    }
}
