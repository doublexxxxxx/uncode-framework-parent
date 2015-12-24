package test;

import javax.transaction.SystemException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uncodeframework.core.hibernate.entity.User;
import com.uncodeframework.core.hibernate.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("/applicationContext.xml"))
public class UserTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	UserService userService;

	@Test
	public void testSave() {
		User t = new User();
		t.setUsername("ida xie");
		t.setEmail("ixie@mmm.com");
		t.setAge(30);
		t.setPhone(6375);
		userService.save(t);
	}
}
