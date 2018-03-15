package com.freesheep.biz.dao;

import com.freesheep.biz.model.OutgoingProductInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OutgoingProductInfoDAO extends AbstractBaseDao<OutgoingProductInfoBO> {

    public OutgoingProductInfoBO selectInfoByOrder(String orderNum, String goodsCode){
        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", orderNum);
        map.put("goodsCode", goodsCode);
        return selectByMap("selectInfoByOrder", map);
    }

}
