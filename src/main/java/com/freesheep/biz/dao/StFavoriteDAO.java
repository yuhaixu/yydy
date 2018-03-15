package com.freesheep.biz.dao;

import com.freesheep.biz.model.StFavoriteBO;
import com.freesheep.biz.model.StPastureBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StFavoriteDAO extends AbstractBaseDao<StFavoriteBO> {

    public Page<StFavoriteBO> selectCollectionList(Pageable pageable, long uid){
        Map<String, Object> map = new HashedMap();
        map.put("uid", uid);
        return selectPageList("selectCollectionList", map, pageable);
    }

    public long selectCollectionCount(int uid , int pid){
        StFavoriteBO bo = new StFavoriteBO();
        bo.setUserId(uid);
        bo.setPrId(pid);
        return selectCount("selectCollectionCount", bo);
    }


}
