package com.uncodeframework.core.hibernate.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.uncodeframework.core.common.bind.annotation.Service;
import com.uncodeframework.core.common.service.BaseServiceImpl;
import com.uncodeframework.core.hibernate.dao.UserDao;
import com.uncodeframework.core.hibernate.entity.User;
import com.uncodeframework.core.hibernate.service.UserService;

@Transactional
@Service(repositoryType = UserDao.class)
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
	
}
