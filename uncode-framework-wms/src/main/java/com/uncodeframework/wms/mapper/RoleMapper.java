package com.uncodeframework.wms.mapper;

import com.uncodeframework.wms.entity.Role;
import java.util.List;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    Role selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    List<Role> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int updateByPrimaryKey(Role record);
}