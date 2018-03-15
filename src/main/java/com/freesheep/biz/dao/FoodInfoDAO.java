package com.freesheep.biz.dao;

import com.freesheep.biz.model.FoodInfoBO;
import com.freesheep.biz.model.StFavoriteBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class FoodInfoDAO extends AbstractBaseDao<FoodInfoBO> {

    public Page<FoodInfoBO> selectFoodInfoList(Pageable pageable){
        Map<String, Object> map = new HashedMap();
        return selectPageList("selectFoodInfoList", map, pageable);
    }

}
