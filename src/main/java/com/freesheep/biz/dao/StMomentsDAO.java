package com.freesheep.biz.dao;

import com.freesheep.biz.model.StMomentsBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StMomentsDAO extends AbstractBaseDao<StMomentsBO> {

    public List<StMomentsBO> selectMomentForHome(List<Integer> midList){
        Map<String, Object> map = new HashMap<>();
        map.put("midList", midList);
        return selectList("selectMomentsForHome", map);
    }

    public StMomentsBO getMoment(int mid){
        Map<String, Object> map = new HashMap<>();
        map.put("id", mid);
        StMomentsBO bo = selectByMap("selectMomentsDataById", map);
        return bo;
    }

    public boolean updateShareCount(StMomentsBO momentsBO){
        return updateById(momentsBO, "updateShareCountById") > 0;
    }

    public boolean updateCommentCount(StMomentsBO momentsBO){
        return updateById(momentsBO, "updateCommentCountById") > 0;
    }

    public List<StMomentsBO> selectMomentList(int mid){
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        return selectList("selectMomentListForOne", map);
    }

    public Page<StMomentsBO> selectMomentIdList(Pageable pageable){
        Map<String, Object> paramMap = new HashMap<>();
        return selectPageList("selectMomentsIdList", paramMap, pageable);
    }

    public List<StMomentsBO> selectMediaList(List<Integer> midList){
        Map<String, Object> map = new HashMap<>();
        map.put("midList", midList);
        return selectList("selectMomentsMediaList", map);
    }

    public List<StMomentsBO> selectMomentsDigList(List<Integer> midList){
        List<StMomentsBO> list = new ArrayList<>();
        for (int i = 0; i < midList.size(); i++){
            StMomentsBO bo = new StMomentsBO();
            bo.setId(midList.get(i));
            List<StMomentsBO> reList = selectList("selectMomentsDigList", bo);
            System.out.println(reList.toString());
            list.addAll(reList);
        }
        return list;
    }
    public List<StMomentsBO> selectMomentsDisList(List<Integer> midList){
        List<StMomentsBO> list = new ArrayList<>();
        for (int i = 0; i < midList.size(); i++){
            StMomentsBO bo = new StMomentsBO();
            bo.setId(midList.get(i));
            List<StMomentsBO> reList = selectList("selectMomentsDisList", bo);
            list.addAll(reList);
        }
        return list;
    }


    public Page<StMomentsBO> selectMomentIdListById(Pageable pageable, int id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return selectPageList("selectMomentsIdListById", paramMap, pageable);
    }

}
