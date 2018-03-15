package com.freesheep.biz.dao;

import com.freesheep.biz.model.StClaimOrderBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StClaimOrderDAO extends AbstractBaseDao<StClaimOrderBO> {

    public Page<StClaimOrderBO> selectOrderListByUid(Pageable pageable, long uid, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        if(type == 0){
            return selectPageList("selectWaitPayOrder", map, pageable);
        } else {
            return selectPageList("selectOrderListByUid", map, pageable);
        }
    }

    public StClaimOrderBO selectOrderDetail(long oid){
        Map<String, Object> map = new HashMap<>();
        map.put("id", oid);
        return selectByMap("selectOrderDetail", map);
    }

    public StClaimOrderBO selectByOrderNum(String orderNum){
        Map<String, Object> map = new HashMap<>();
        map.put("oid", orderNum);
        return selectByMap("selectByOrderNum", map);
    }

}
