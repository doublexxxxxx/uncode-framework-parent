package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncodeframework.core.common.mail.simple.MailUtil;

import freemarker.template.TemplateException;

/**
 * 邮件工具测试类
 */
public class MailUtilTest {
	private static Logger log = LoggerFactory.getLogger(MailUtilTest.class);

	@Test
	public void testMailTemplate() {
		String templateName = "template_1.ftl";
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "test");
		try {
			MailUtil.sendMailByTemplate("ixie@mmm.com", "test", map, templateName);
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (TemplateException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMail() {
		try {
			MailUtil.sendMail("75933651@qq.com", "test", "普通邮件");
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMailAndFile() {
		try {
			String filePath = "D:/dat.txt";
			MailUtil.sendMailAndFile("75933651@qq.com", "test", filePath, "ted");
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}
}
