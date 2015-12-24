package com.uncodeframework.wms.mapper;

import com.uncodeframework.wms.entity.Employee;
import java.util.List;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    Employee selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    List<Employee> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employee
     *
     * @mbggenerated Thu Dec 17 14:35:10 CST 2015
     */
    int updateByPrimaryKey(Employee record);
}