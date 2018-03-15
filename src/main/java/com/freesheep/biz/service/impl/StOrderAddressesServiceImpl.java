package com.freesheep.biz.service.impl;

import com.freesheep.biz.model.StOrderAddressesBO;
import com.freesheep.biz.service.StOrderAddressesService;
import org.springframework.stereotype.Service;

@Service
public class StOrderAddressesServiceImpl implements StOrderAddressesService {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StOrderAddressesBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StOrderAddressesBO record) {
        return 0;
    }

    @Override
    public StOrderAddressesBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StOrderAddressesBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StOrderAddressesBO record) {
        return 0;
    }
}
