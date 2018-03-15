package com.freesheep.biz.dao;

import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StDeviceInfoDAO extends AbstractBaseDao<StDeviceInfoBO> {

    public long getStepCount(long deviceId){
        Map<String, Object> map = new HashedMap();
        map.put("did", deviceId);
        return selectMapCount("selectStepCount", map);
    }

    public List<StDeviceInfoBO> getHistoryInfo(long deviceId, String startTime, String endTime){
        Map<String, Object> map = new HashedMap();
        map.put("start", startTime);
        map.put("end", endTime);
        map.put("device_id", deviceId);
        return selectList("selectHistoryInfo", map);
    }

    public long getCountForTime(long deviceId, String start, String end){
        Map<String, Object> map = new HashedMap();
        map.put("start", start);
        map.put("end", end);
        map.put("did", deviceId);
        return selectMapCount("selectCountInTime", map);
    }

}
