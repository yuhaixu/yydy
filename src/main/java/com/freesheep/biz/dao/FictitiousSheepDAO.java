package com.freesheep.biz.dao;

import com.freesheep.biz.model.FictitiousSheepBO;
import com.freesheep.common.extend.AbstractBaseDao;
import com.freesheep.web.util.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FictitiousSheepDAO extends AbstractBaseDao<FictitiousSheepBO> {

    public long getClaimCount(long uid){
        FictitiousSheepBO bo = new FictitiousSheepBO();
        bo.setUserId(uid);
        return selectCount("selectClaimCount", bo);
    }

    public Page<FictitiousSheepBO> getSheepList(Pageable pageable){
        Map<String, Object> map = new HashMap<>();
        return selectPageList("selectSheepList", map, pageable);
    }

    public long sheepCountByUid(long userId){
        FictitiousSheepBO sheepBO = new FictitiousSheepBO();
        sheepBO.setUserId(userId);
        return selectCount("selectSheepCountByUid", sheepBO);
    }

    public int lockSheepById(long id, long uid){
        FictitiousSheepBO bo = new FictitiousSheepBO();
        bo.setId(id);
        bo.setSheepStatus(2);
        bo.setOperateUid(uid);
        bo.setModifyTime(new Date());
        return updateById(bo, "lockSheepById");
    }

    public int unLockSheepById(long id){
        FictitiousSheepBO bo = new FictitiousSheepBO();
        bo.setId(id);
        bo.setSheepStatus(0);
        bo.setModifyTime(new Date());
        return updateById(bo, "unlockSheepById");
    }

    public List<FictitiousSheepBO> selectClaimList(long uid){
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        return selectList("selectClaimList", map);
    }

    public FictitiousSheepBO selectSheepInfo(long id){
        String start = DateUtils.getNowDate();
        String end = DateUtils.beforeDay(start, -7);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("start", start + " 00:00:00");
        map.put("end", end + " 23:59:59");
        List<FictitiousSheepBO> list = selectList("selectSheepInfo", map);
        if(list == null || list.size() < 1) return null;
        return list.get(0);
    }

}
