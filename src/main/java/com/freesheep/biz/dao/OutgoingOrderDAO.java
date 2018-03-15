package com.freesheep.biz.dao;

import com.freesheep.biz.model.OutgoingOrderBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OutgoingOrderDAO extends AbstractBaseDao<OutgoingOrderBO> {

    public OutgoingOrderBO selectOutgoingOrderByDeliveryOrderCode(String deliverOrderCode){
        Map<String, Object> map = new HashMap<>();
        map.put("docId", deliverOrderCode);
        return selectByMap("selectOutgoingOrderByDeliveryOrderCode", map);
    }

}
