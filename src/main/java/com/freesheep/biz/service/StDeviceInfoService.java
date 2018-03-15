package com.freesheep.biz.service;

import com.freesheep.biz.model.StDeviceInfoBO;

import java.util.List;

public interface StDeviceInfoService {

    int insertSelective(StDeviceInfoBO bo);
    long getStepCount(long deviceId);
    List<StDeviceInfoBO> getHistoryInfo(long deviceId, String startTime, String endTime);
    long getCountForTime(long deviceId, String start, String end);
}