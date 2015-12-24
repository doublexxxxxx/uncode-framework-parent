package com.uncodeframework.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("/applicationContext-mail.xml"))
public class CommonTest {

	@BeforeClass
	public static void beforeClass() throws Exception {
		// ClassPathXmlApplicationContext app = new
		// ClassPathXmlApplicationContext("classpath:applicationContext-jndi.xml");
		// DataSource ds1 = (DataSource) app.getBean("dataSource");
		// SimpleNamingContextBuilder builder1 = new
		// SimpleNamingContextBuilder();
		// builder1.bind("jdbc/scms/scms_datasource", ds1);
		// builder1.activate();
	}
}
