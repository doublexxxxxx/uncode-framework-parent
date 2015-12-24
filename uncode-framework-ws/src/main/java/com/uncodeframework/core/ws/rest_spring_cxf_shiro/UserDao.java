package com.uncodeframework.core.ws.rest_spring_cxf_shiro;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private static final Map<String, String> accountMap = new HashMap<String, String>();
    private static final MultivaluedMap<String, String> userRoleMap = new MultivaluedHashMap<String, String>();
    private static final MultivaluedMap<String, String> rolePermissionMap = new MultivaluedHashMap<String, String>();

    static {
        accountMap.put("jack", CodecUtil.encryptMD5("jack"));
        accountMap.put("rose", CodecUtil.encryptMD5("rose"));

        userRoleMap.put("jack", Arrays.asList("admin"));
        userRoleMap.put("rose", Arrays.asList("guest"));

        rolePermissionMap.put("admin", Arrays.asList("product.c", "product.r", "product.u", "product.d"));
        rolePermissionMap.put("guest", Arrays.asList("product.r"));
    }

    public String queryPassword(String username) {
        return accountMap.get(username);
    }

    public Set<String> queryRoleNameSet(String username) {
        return new HashSet<String>(userRoleMap.get(username));
    }

    public Set<String> queryPermissionNameSet(String username) {
        Set<String> permissionNameSet = new HashSet<String>();
        Set<String> roleNameSet = queryRoleNameSet(username);
        for (String roleName : roleNameSet) {
            Collection<String> permissionNames = rolePermissionMap.get(roleName);
            if (permissionNames != null) {
                permissionNameSet.addAll(permissionNames);
            }
        }
        return permissionNameSet;
    }
}
