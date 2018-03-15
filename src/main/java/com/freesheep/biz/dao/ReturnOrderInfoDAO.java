package com.freesheep.biz.dao;

import com.freesheep.biz.model.ReturnOrderInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReturnOrderInfoDAO extends AbstractBaseDao<ReturnOrderInfoBO> {

    public ReturnOrderInfoBO selectReturnOrderByNum(String orderNum, String systemOrderNum, String goodsCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("on", orderNum);
        map.put("sn", systemOrderNum);
        map.put("gc", goodsCode);
        return selectByMap("selectReturnOrderByNum", map);
    }

}
