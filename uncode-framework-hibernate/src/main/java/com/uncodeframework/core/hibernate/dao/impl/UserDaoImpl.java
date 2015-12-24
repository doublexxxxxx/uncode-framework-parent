package com.uncodeframework.core.hibernate.dao.impl;


import com.uncodeframework.core.common.bind.annotation.Repository;
import com.uncodeframework.core.common.orm.hibernate.BaseRepoitoryImpl;
import com.uncodeframework.core.hibernate.dao.UserDao;
import com.uncodeframework.core.hibernate.entity.User;

@Repository(entity = User.class)
public class UserDaoImpl extends BaseRepoitoryImpl<User, String> implements UserDao {

}
