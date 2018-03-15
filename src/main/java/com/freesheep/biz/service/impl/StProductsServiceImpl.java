package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StProductInfoDAO;
import com.freesheep.biz.dao.StProductsDAO;
import com.freesheep.biz.dao.StProductsImagesDAO;
import com.freesheep.biz.model.StProductInfoBO;
import com.freesheep.biz.model.StProductsBO;
import com.freesheep.biz.model.StProductsImagesBO;
import com.freesheep.biz.service.StProductsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StProductsServiceImpl implements StProductsService {

    @Resource
    protected StProductsDAO productsDAO;
    @Resource
    protected StProductInfoDAO productInfoDAO;
    @Resource
    protected StProductsImagesDAO productsImagesDAO;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StProductsBO record) {
        return 0;
    }

    @Override
    public long insertSelective(StProductsBO record) {
        int low = productsDAO.insertSelective(record);
        if (low < 1) return 0;
        return record.getId();
    }

    @Override
    public StProductsBO selectByPrimaryKey(Integer id) {
        return productsDAO.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StProductsBO record) {
        return productsDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(StProductsBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StProductsBO record) {
        return 0;
    }

    @Override
    public List<StProductsBO> getProductList() {
        return productsDAO.selectProductList();
    }

    @Override
    public Page<StProductsBO> getAllList(PageRequest pageRequest) {
        return productsDAO.selectAllProList(pageRequest);
    }

    @Override
    public StProductsBO getProductInfoById(long id) {
        List<StProductInfoBO> list = productInfoDAO.selectInfoList(id);
        List<StProductsImagesBO> imgList = productsImagesDAO.getImageList(id);
        StProductsBO stProductsBO = productsDAO.selectProductInfoById(id);
        stProductsBO.setInfoList(list);
        stProductsBO.setImgList(imgList);
        return stProductsBO;
    }
}
