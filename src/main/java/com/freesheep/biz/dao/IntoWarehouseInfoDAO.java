package com.freesheep.biz.dao;

import com.freesheep.biz.model.IntoWarehouseInfoBO;
import com.freesheep.biz.model.IntoWarehouseOrderBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IntoWarehouseInfoDAO extends AbstractBaseDao<IntoWarehouseInfoBO> {

    public IntoWarehouseInfoBO selectInfoByOrder(String orderNum, String goodsNum){
        Map<String, Object> map = new HashMap<>();
        map.put("on", orderNum);
        map.put("gn", goodsNum);
        return selectByMap("selectIntoWarehouseInfoByOrder", map);
    }

}
