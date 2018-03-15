package com.freesheep.biz.dao;

import com.freesheep.biz.model.ProductCommentBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ProductCommentDAO extends AbstractBaseDao<ProductCommentBO> {

    public Page<ProductCommentBO> getCommentList(Pageable pageable, int pid){
        Map<String, Object> map = new HashedMap();
        map.put("pid", pid);
        return selectPageList("selectProCommentList", map, pageable);
    }

}
