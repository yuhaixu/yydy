package com.freesheep.biz.dao;

import com.freesheep.biz.model.StDeviceBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StDeviceDAO extends AbstractBaseDao<StDeviceBO> {

    public StDeviceBO selectByDeviceNum(String deviceNum){
        Map<String, Object> map = new HashMap<>();
        map.put("deviceNum", deviceNum);
        return selectByMap("selectByDeviceNum", map);
    }

}
