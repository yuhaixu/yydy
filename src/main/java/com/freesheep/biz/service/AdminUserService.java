package com.freesheep.biz.service;

import com.freesheep.biz.model.AdminUserBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminUserService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    int insert(AdminUserBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    int insertSelective(AdminUserBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    AdminUserBO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdminUserBO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdminUserBO record);

    AdminUserBO selectByUserName(String userName);

    Page<AdminUserBO> getAdminUserList(Pageable pageable);
    AdminUserBO selectByUserId(Long id);
}