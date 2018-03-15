package com.freesheep.biz.dao;

import com.freesheep.biz.model.StMomentsDissBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StMomentsDissDAO extends AbstractBaseDao<StMomentsDissBO> {

    public boolean insertComment(StMomentsDissBO momentsDissBO) {
        int row = insertSelective(momentsDissBO);
        return row > 0;
    }

    public List<StMomentsDissBO> selectMomentListForMid(int mid){
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        return selectList("selectMomentListForMid", map);
    }

}
