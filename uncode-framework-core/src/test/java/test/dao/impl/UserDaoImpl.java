package test.dao.impl;


import org.uncodeframework.core.common.bind.annotation.Repository;
import org.uncodeframework.core.common.orm.hibernate.BaseRepoitoryImpl;

import test.dao.UserDao;
import test.entity.User;

@Repository(entity = User.class)
public class UserDaoImpl extends BaseRepoitoryImpl<User, String> implements UserDao {

}
