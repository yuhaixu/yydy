package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.FoodInfoDAO;
import com.freesheep.biz.model.FoodInfoBO;
import com.freesheep.biz.service.FoodInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {

    @Resource
    FoodInfoDAO foodInfoDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return foodInfoDAO.deleteById(id);
    }

    @Override
    public int insert(FoodInfoBO record) {
        return 0;
    }

    @Override
    public int insertSelective(FoodInfoBO record) {
        return foodInfoDAO.insertSelective(record);
    }

    @Override
    public FoodInfoBO selectByPrimaryKey(Long id) {
        return foodInfoDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FoodInfoBO record) {
        return foodInfoDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(FoodInfoBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FoodInfoBO record) {
        return 0;
    }

    @Override
    public Page<FoodInfoBO> getFoodInfoList(Pageable pageable) {
        return foodInfoDAO.selectFoodInfoList(pageable);
    }
}
