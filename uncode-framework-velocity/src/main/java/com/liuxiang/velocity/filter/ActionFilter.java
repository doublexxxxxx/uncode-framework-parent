package com.liuxiang.velocity.filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuxiang.velocity.action.StudentAction;
import com.liuxiang.velocity.framework.WebApplicationContext;
import com.liuxiang.velocity.framework.WebContext;
import com.liuxiang.velocity.util.ClassUtil;

/**
 * Servlet Filter implementation class ActionFilter
 */
public class ActionFilter implements Filter {
	private static WebContext ctx;
	public static ThreadLocal<ServletResponse> threadLocalResponse = new ThreadLocal<ServletResponse>();
    /**
     * Default constructor. 
     */
    public ActionFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(ctx == null) {
    		ctx = new WebApplicationContext();
    		ctx.init();
    	}
		threadLocalResponse.set(response);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String actionAndMethod = uri.substring(uri.lastIndexOf("/") + 1);
		actionAndMethod = actionAndMethod.substring(0,actionAndMethod.lastIndexOf("."));
		String[] actionAndMethodArray = actionAndMethod.split("!");
		String actionName = actionAndMethodArray[0];
		String methodName = null;
		if(actionAndMethodArray.length == 1) {
			methodName = "execute";
		} else {
			methodName = actionAndMethodArray[1];
		}
		Object action = ctx.getActionInstance(actionName);
		String result = null;
		this.setValueToAction(action, req);
		
		/*Enumeration enumeration = req.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String paramName,paramValueStr;
			paramName = (String) enumeration.nextElement();
			paramValueStr = req.getParameter(paramName);
			if(paramValueStr!=null && !"".equals(paramValueStr)) {
				String _methodName = "set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);
				Class clazz = action.getClass();
				try {
					Method m = clazz.getMethod(_methodName);
					if(m != null) {
						Class paramClazz = m.getParameterTypes()[0];
						if(paramClazz.equals(String.class)) {
							m.invoke(action, paramValueStr);
						} else if(paramClazz.equals(Integer.class)) {
							m.invoke(action,Integer.parseInt(paramValueStr));
						} else if(paramClazz.equals(Date.class)) {
							DateFormat df = SimpleDateFormat.getDateInstance();
							m.invoke(action,df.parse(paramValueStr));
						}
					}
				} catch (NoSuchMethodException e) {
					System.out.println("No paramter for " + paramName);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}*/
		try {
			result = (String) action.getClass().getMethod(methodName).invoke(action);
			setAttrToRequest(action,req);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if(result != null) {
			req.getRequestDispatcher(result).forward(req,resp);
		}
	}
	
	private void setAttrToRequest(Object action, HttpServletRequest req) {
		try {
			Method[] methods = action.getClass().getDeclaredMethods();
			for(Method m : methods) {
				String _methodName = m.getName();
				if(_methodName.startsWith("get")) {
					Object _paramValue = m.invoke(action);
					if(_paramValue != null) {
						String _paramName = ClassUtil.convertMethodToName(_methodName);
						req.setAttribute(_paramName, _paramValue);
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void setValueToAction(Object action,HttpServletRequest req) {
		try {
			Method[] methods = action.getClass().getDeclaredMethods();
			for(Method m : methods) {
				String _methodName = m.getName();
				if(_methodName.startsWith("set")) {
					String _paramName = ClassUtil.convertMethodToName(_methodName);
					String _paramValue = req.getParameter(_paramName);
					if(_paramValue != null && !"".equals(_paramValue)) {
						Class paramClazz = m.getParameterTypes()[0];
						if(paramClazz.equals(String.class)) {
							m.invoke(action, _paramValue);
						} else if(paramClazz.equals(Integer.class)) {
							m.invoke(action,Integer.parseInt(_paramValue));
						} else if(paramClazz.equals(Date.class)) {
							DateFormat df = SimpleDateFormat.getDateInstance();
							m.invoke(action,df.parse(_paramValue));
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
