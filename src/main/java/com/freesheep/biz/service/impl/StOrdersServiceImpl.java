package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StOrdersDAO;
import com.freesheep.biz.dao.StProductsDAO;
import com.freesheep.biz.model.StOrdersBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.service.StOrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class StOrdersServiceImpl implements StOrdersService {

    @Resource
    StOrdersDAO ordersDAO;
    @Resource
    StProductsDAO productsDAO;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StOrdersBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StOrdersBO record) {
        return ordersDAO.insertSelective(record);
    }

    @Override
    public StOrdersBO selectByPrimaryKey(Integer id) {
        return ordersDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StOrdersBO record) {
        return ordersDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(StOrdersBO record) {
        return 0;
    }

    @Override
    public Page<StOrdersBO> getOrderList(Pageable pageable, long uid, int type) {
        Page<StOrdersBO> page = ordersDAO.selectCollectionList(pageable, uid, type);
        List<StOrdersBO> list = new ArrayList<>();
        list.addAll(page.getContent());
        if(list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                StOrdersBO ordersBO = list.get(i);
                List<StProductsBO> proList = productsDAO.selectOrderPro(ordersBO.getId());
                if(proList != null && proList.size() > 0) ordersBO.setProductList(proList);
                list.set(i, ordersBO);
            }
        }
        return page;
    }

    @Override
    public Page<StOrdersBO> getOrderList(Pageable pageable, int type) {
        Page<StOrdersBO> page = ordersDAO.selectCollectionList(pageable, type);
        List<StOrdersBO> list = new ArrayList<>();
        list.addAll(page.getContent());
        if(list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                StOrdersBO ordersBO = list.get(i);
                List<StProductsBO> proList = productsDAO.selectOrderPro(ordersBO.getId());
                if(proList != null && proList.size() > 0) ordersBO.setProductList(proList);
                list.set(i, ordersBO);
            }
        }
        return page;
    }

    @Override
    public StOrdersBO getOrderDetails(long oid) {
        StOrdersBO ordersBO = ordersDAO.selectOrderDetails(oid);
        List<StProductsBO> proList = productsDAO.selectOrderPro(ordersBO.getId());
        if(proList != null && proList.size() > 0) ordersBO.setProductList(proList);
        return ordersBO;
    }
}
