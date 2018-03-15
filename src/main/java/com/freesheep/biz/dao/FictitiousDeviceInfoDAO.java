package com.freesheep.biz.dao;

import com.freesheep.biz.model.FictitiousDeviceInfoBO;
import com.freesheep.biz.model.StDeviceInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FictitiousDeviceInfoDAO extends AbstractBaseDao<FictitiousDeviceInfoBO> {

    public List<FictitiousDeviceInfoBO> getHistoryInfo(long deviceId, String startTime, String endTime){
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
        return selectMapCount("selectFreeCountInTime", map);
    }

}
