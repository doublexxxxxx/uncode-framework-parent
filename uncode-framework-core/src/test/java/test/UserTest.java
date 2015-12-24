package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.entity.User;
import test.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("/applicationContext.xml"))
public class UserTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	UserService userService;

	@Test
	public void testSave() {
		User t = new User();
		t.setUsername("gxh");
		t.setEmail("ixie@mmm.com");
		t.setAge(30);
		t.setPhone(6375);
		userService.save(t);
	}

	@Test
	public void testFindList() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "402881ee51722a9e0151722aa61a0000");
		System.out.println(userService.findList("select", params));
	}

	@Test
	public void testCache() {
		User user = new User();
		user.setUsername("xiexiao");
		user.setAge(24);
		User u = userService.save(user);
		System.out.println(userService.findObject(u.getId()));
	}

	@Test
	public void testDelete() {
		User t = new User();
		t.setId("402881ee51722a9e0151722aa61a0000");
		userService.remove(t);
	}
	
	@Test
	public void testUpdate() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "402881ee51754aa80151754aba510001");
		params.put("username", "bbbb");
		userService.update("update", params);
	}
}
