package com.uncodeframework.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.uncodeframework.core.plugins.email.IMailSendService;
import com.uncodeframework.core.plugins.email.MailBean;
import com.uncodeframework.core.plugins.email.MailException;
import com.uncodeframework.core.spring.SpringHelper;

public class MailSendTest {//extends CommonTest {

	@Test
	public void sendWithTemplate() {
		try {
			//ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext-mail.xml");
			IMailSendService mailSendService = (IMailSendService) SpringHelper.getBean("mailSenderService");
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
