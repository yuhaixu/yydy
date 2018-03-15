package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StClaimOrderDAO;
import com.freesheep.biz.model.StClaimOrderBO;
import com.freesheep.biz.service.StClaimOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StClaimOrderServiceImpl implements StClaimOrderService {

    @Resource
    StClaimOrderDAO claimOrderDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(StClaimOrderBO record) {
        return 0;
    }

    @Override
    public long insertSelective(StClaimOrderBO record) {
        int low = claimOrderDAO.insertSelective(record);
        if (low < 1) return 0;
        return record.getId();
    }

    @Override
    public StClaimOrderBO selectByPrimaryKey(Long id) {
        return claimOrderDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StClaimOrderBO record) {
        return claimOrderDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(StClaimOrderBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StClaimOrderBO record) {
        return claimOrderDAO.updateById(record);
    }

    @Override
    public Page<StClaimOrderBO> selectOrderListByUid(Pageable pageable, long uid, int type) {
        return claimOrderDAO.selectOrderListByUid(pageable, uid, type);
    }

    @Override
    public StClaimOrderBO selectOrderDetail(long oid) {
        return claimOrderDAO.selectOrderDetail(oid);
    }

    @Override
    public StClaimOrderBO selectByOrderNum(String orderNum) {
        return claimOrderDAO.selectByOrderNum(orderNum);
    }

}
