package com.freesheep.biz.dao;

import com.freesheep.biz.model.StProductInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StProductInfoDAO extends AbstractBaseDao<StProductInfoBO> {

    public StProductInfoBO selectInfoByPid(long pid){
        Map<String, Object> map = new HashMap<>();
        map.put("pid", pid);
        return selectByMap("selectInfoByPid", map);
    }

    public List<StProductInfoBO> selectInfoList(long pid){
        Map<String, Object> map = new HashMap<>();
        map.put("pid", pid);
        return selectList("selectInfoListByPid", map);
    }

}
