package com.uncodeframework.core.cluster_cloud.core.bean;

import java.util.*;
import java.io.Serializable;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午04:38:53]
 */
public class Employee implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String employeeEnl;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeEnl() {
		return employeeEnl;
	}
	public void setEmployeeEnl(String employeeEnl) {
		this.employeeEnl = employeeEnl;
	}
	public String toString() {
		return "Employee [id=" + id + ",employeeEnl=" + employeeEnl + "]";
	}
}
