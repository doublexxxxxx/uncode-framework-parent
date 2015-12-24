package com.liuxiang.velocity.util.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.liuxiang.velocity.action.StudentAction;
import com.liuxiang.velocity.dao.StudentDao;
import com.liuxiang.velocity.framework.WebApplicationContext;
import com.liuxiang.velocity.framework.WebContext;

public class ApplicationContextTest {
	public static WebContext ctx;

	@BeforeClass
	public static void before() {
		ctx = new WebApplicationContext();
		/*
		 * String[] packages = {"com.liuxiang.velocity"}; ctx.init(packages);
		 */
		ctx.init();
	}

	@Test
	public void test() {
		StudentAction s1 = (StudentAction) ctx.getActionInstance("studentAction");
		StudentAction s2 = (StudentAction) ctx.getActionInstance("studentAction");
		StudentDao sd1 = (StudentDao) ctx.getInstance("com.liuxiang.velocity.dao.StudentDao");
		StudentDao sd2 = (StudentDao) ctx.getInstance("com.liuxiang.velocity.dao.StudentDao");
	}
}
