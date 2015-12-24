package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uncodeframework.core.common.mail.spring.IMailSendService;
import org.uncodeframework.core.common.mail.spring.MailBean;
import org.uncodeframework.core.common.mail.spring.MailException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("/spring-config-mail.xml"))
public class MailSpringTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	IMailSendService mailSendService;
	
	@Test
	public void sendWithTemplate() {
		try {
			//ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-mail.xml");
			//IMailSendService mailSendService = (IMailSendService) SpringHelper.getBean("mailSenderService");
			MailBean mailBean = new MailBean();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("username", "UNCODE");
			mailBean.setSubject("UNCODE Send With Template");
			mailBean.setTo(new String[] { "ixie@mmm.com" });
			mailBean.setTemplateName("mail/templates/mail.vm");
			mailBean.setModel(model);
			mailSendService.sendWithTemplate(mailBean);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
}
