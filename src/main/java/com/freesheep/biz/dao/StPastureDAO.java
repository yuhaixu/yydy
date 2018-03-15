package com.freesheep.biz.dao;

import com.freesheep.biz.model.StPastureBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StPastureDAO extends AbstractBaseDao<StPastureBO> {

    public Page<StPastureBO> getPastureList(Pageable pageable){
        Map<String, Object> map = new HashedMap();
        return selectPageList("getPastureList", map, pageable);
    }

}
