package com.freesheep.biz.dao;

import com.freesheep.biz.model.IntoWarehouseOrderBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IntoWarehouseOrderDAO extends AbstractBaseDao<IntoWarehouseOrderBO> {

    public IntoWarehouseOrderBO selectIntoWarehouseOrderByNum(String systemNum){
        Map<String, Object> map = new HashMap<>();
        map.put("sid", systemNum);
        return selectByMap("selectIntoWarehouseOrderByNum", map);
    }

}
