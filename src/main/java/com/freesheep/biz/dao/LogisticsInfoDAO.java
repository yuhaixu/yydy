package com.freesheep.biz.dao;

import com.freesheep.biz.model.LogisticsInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LogisticsInfoDAO extends AbstractBaseDao<LogisticsInfoBO> {

    public LogisticsInfoBO selectInfoByDeliveryOrderId(String doId){
        Map<String, Object> map = new HashMap<>();
        map.put("doid", doId);
        return selectByMap("selectInfoByDeliveryOrderId", map);
    }

}
