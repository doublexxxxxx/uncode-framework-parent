package com.uncodeframework.wms.service;

import java.util.List;

import com.uncodeframework.wms.entity.Employee;

public interface IUserService {
	
	public Employee getUserById(String userId);
	
	public List<Employee> getUsers();
	
	public int insert(Employee userInfo); 

}