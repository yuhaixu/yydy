package com.freesheep.biz.dao;

import com.freesheep.biz.model.StProductsBO;
import com.freesheep.common.extend.AbstractBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StProductsDAO extends AbstractBaseDao<StProductsBO> {

    public List<StProductsBO> selectProductForHome(List<Integer> pidList){
        Map<String, Object> map = new HashMap<>();
        map.put("pidList", pidList);
        return selectList("selectProductsForHome", map);
    }

    public List<StProductsBO> selectOrderPro(int oid){
        Map<String, Object> map = new HashMap<>();
        map.put("oid", oid);
        return selectList("selectOrderPro", map);
    }

    public List<StProductsBO> selectProductList(){
        Map<String, Object> map = new HashMap<>();
        return selectList("selectProductList", map);
    }

    public Page<StProductsBO> selectAllProList(PageRequest pageRequest){
        Map<String, Object> map = new HashMap<>();
        return selectPageList("selectAllProList",map, pageRequest);
    }

    public StProductsBO selectProductInfoById(long id){
        Map<String, Object> map = new HashMap<>();
        map.put("pid", id);
        return selectByMap("selectProductInfoById", map);
    }

}
