package test.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.uncodeframework.core.common.bind.annotation.Service;
import org.uncodeframework.core.common.service.BaseServiceImpl;

import test.dao.UserDao;
import test.entity.User;
import test.service.UserService;

@Transactional
@Service(repositoryType = UserDao.class)
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
	
}
