package com.freesheep.biz.dao;

import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StOrdersDAO extends AbstractBaseDao<StOrdersBO> {

    public Page<StOrdersBO> selectCollectionList(Pageable pageable, long uid, int type){
        Map<String, Object> map = new HashedMap();
        map.put("uid", uid);
        map.put("type", type);
        return selectPageList("selectOrderList", map, pageable);
    }

    public Page<StOrdersBO> selectCollectionList(Pageable pageable,int type){
        Map<String, Object> map = new HashedMap();
        map.put("type", type);
        return selectPageList("selectAllOrderList", map, pageable);
    }

    public StOrdersBO selectOrderDetails(long oid){
        Map<String, Object> map = new HashedMap();
        map.put("oid", oid);
        return selectByMap("selectOrderDetailsById", map);
    }

}
