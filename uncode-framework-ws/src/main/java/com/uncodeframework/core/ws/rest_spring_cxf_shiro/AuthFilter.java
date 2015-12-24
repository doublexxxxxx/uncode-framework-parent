package com.uncodeframework.core.ws.rest_spring_cxf_shiro;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.authz.aop.AuthenticatedAnnotationHandler;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.authz.aop.GuestAnnotationHandler;
import org.apache.shiro.authz.aop.PermissionAnnotationHandler;
import org.apache.shiro.authz.aop.RoleAnnotationHandler;
import org.apache.shiro.authz.aop.UserAnnotationHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements ContainerRequestFilter {

    /**
     * 定义 Shiro 权限注解与注解处理器之间的映射关系
     */
    private static Map<Class<? extends Annotation>, AuthorizingAnnotationHandler> shiroAnnotationMap =
        new LinkedHashMap<Class<? extends Annotation>, AuthorizingAnnotationHandler>();

    static {
        shiroAnnotationMap.put(RequiresAuthentication.class, new AuthenticatedAnnotationHandler());
        shiroAnnotationMap.put(RequiresUser.class, new UserAnnotationHandler());
        shiroAnnotationMap.put(RequiresGuest.class, new GuestAnnotationHandler());
        shiroAnnotationMap.put(RequiresRoles.class, new RoleAnnotationHandler());
        shiroAnnotationMap.put(RequiresPermissions.class, new PermissionAnnotationHandler());
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // 获取目标方法
        Method method = getTargetMethod();
        // 获取 Shiro 注解（可带有多个注解）
        Set<Annotation> annotationSet = getShiroAnnotationSet(method);
        for (Annotation annotation : annotationSet) {
            // 创建 Shiro 权限注解处理器
            AuthorizingAnnotationHandler handler = getAuthorizingAnnotationHandler(annotation);
            try {
                // 让 Shiro 进行认证与授权
                handler.assertAuthorized(annotation);
            } catch (AuthorizationException e) {
                handleException(requestContext, e);
            }
        }
    }

    private Method getTargetMethod() {
        Message message = JAXRSUtils.getCurrentMessage();
        Exchange exchange = message.getExchange();
        OperationResourceInfo operationResourceInfo = exchange.get(OperationResourceInfo.class);
        return operationResourceInfo.getAnnotatedMethod();
    }

    private Set<Annotation> getShiroAnnotationSet(Method method) {
        Set<Annotation> annotationSet = new LinkedHashSet<Annotation>();
        Class<?> cls = method.getDeclaringClass();
        addClassAnnotation(annotationSet, cls);
        addMethodAnnotation(annotationSet, method);
        return annotationSet;
    }

    private void addClassAnnotation(Set<Annotation> annotationSet, Class<?> cls) {
        for (Class<? extends Annotation> shiroAnnotationClass : shiroAnnotationMap.keySet()) {
            if (cls.isAnnotationPresent(shiroAnnotationClass)) {
                annotationSet.add(cls.getAnnotation(shiroAnnotationClass));
            }
        }
    }

    private void addMethodAnnotation(Set<Annotation> annotationSet, Method method) {
        for (Class<? extends Annotation> shiroAnnotationClass : shiroAnnotationMap.keySet()) {
            if (method.isAnnotationPresent(shiroAnnotationClass)) {
                annotationSet.add(method.getAnnotation(shiroAnnotationClass));
            }
        }
    }

    private AuthorizingAnnotationHandler getAuthorizingAnnotationHandler(Annotation annotation) {
        return shiroAnnotationMap.get(annotation.annotationType());
    }

    private void handleException(ContainerRequestContext requestContext, AuthorizationException e) {
        int code;
        if (e instanceof UnauthenticatedException) {
            // 认证失败
            code = Response.Status.UNAUTHORIZED.getStatusCode(); // 401
        } else if (e instanceof UnauthorizedException) {
            // 授权失败
            code = Response.Status.FORBIDDEN.getStatusCode(); // 403
        } else {
            throw new IllegalStateException("can not support this exception: " + e.getClass().getName(), e);
        }
        requestContext.abortWith(Response.status(code).build());
    }
}
