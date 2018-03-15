package com.freesheep.biz.dao;

import com.freesheep.biz.model.ShowInfoBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShowInfoDAO extends AbstractBaseDao<ShowInfoBO> {

    public List<ShowInfoBO> getShowInfo(){
        Map<String, Object> map = new HashMap<>();
        return selectList("selectAllShowInfo", map);
    }

}
