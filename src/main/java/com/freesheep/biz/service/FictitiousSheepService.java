package com.freesheep.biz.service;

import com.freesheep.biz.model.FictitiousSheepBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FictitiousSheepService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    int insert(FictitiousSheepBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    int insertSelective(FictitiousSheepBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    FictitiousSheepBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FictitiousSheepBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fictitious_sheep
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FictitiousSheepBO record);

    long getClaimCount(long uid);
    Page<FictitiousSheepBO> selectSheepList(Pageable pageable);
    long sheepCountByUid(long userId);
    int lockSheepById(long id, long uid);
    int unLockSheepById(long id);
    List<FictitiousSheepBO> selectClaimList(long uid);
    FictitiousSheepBO selectSheepInfo(long id);
}