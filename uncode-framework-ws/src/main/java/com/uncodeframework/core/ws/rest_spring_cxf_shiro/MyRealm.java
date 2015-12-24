package com.uncodeframework.core.ws.rest_spring_cxf_shiro;

import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于认证与鉴权
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    public MyRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取表单提交过来的数据
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 获取用户名
        String username = usernamePasswordToken.getUsername();
        // 根据用户名查询出密码（已加密）
        String password = userDao.queryPassword(username);
        // 返回用户名与密码封装的对象（将传递给所对应的 Matcher）
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        info.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        info.setCredentials(password);
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户名
        String username = (String) super.getAvailablePrincipal(principals);
        // 获取角色名集合
        Set<String> roleNameSet = userDao.queryRoleNameSet(username);
        // 获取权限名集合
        Set<String> permissionNameSet = userDao.queryPermissionNameSet(username);
        // 返回角色名集合与权限名集合封装的对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNameSet);
        info.setStringPermissions(permissionNameSet);
        return info;
    }
}
