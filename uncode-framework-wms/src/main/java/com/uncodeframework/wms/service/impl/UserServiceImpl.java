package com.uncodeframework.wms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uncodeframework.wms.entity.Employee;
import com.uncodeframework.wms.mapper.EmployeeMapper;
import com.uncodeframework.wms.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private EmployeeMapper userDao;

	@Override
	public Employee getUserById(String userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public List<Employee> getUsers() {
		return this.userDao.selectAll();
	}

	@Override
	public int insert(Employee userInfo) {
		return this.userDao.insert(userInfo);
	}
}