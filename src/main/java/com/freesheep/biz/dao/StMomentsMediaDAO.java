package com.freesheep.biz.dao;

import com.freesheep.biz.model.StMomentsMediaBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StMomentsMediaDAO extends AbstractBaseDao<StMomentsMediaBO> {

    public List<StMomentsMediaBO> selectMediaForMid(int mid){
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        return selectList("selectMediaForMid", map);
    }

    public boolean insertMediaList(List<StMomentsMediaBO> mediaBOList){
        int row = getSqlSessionTemplate().insert("saveMediaList", mediaBOList);
        System.out.println("插入的行数 ： " + row);
        return row > 0;
    }

}
