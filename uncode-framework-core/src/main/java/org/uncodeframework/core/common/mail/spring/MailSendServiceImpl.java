package org.uncodeframework.core.common.mail.spring;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MailSendServiceImpl implements IMailSendService {

	private Logger log = LoggerFactory.getLogger(MailSendServiceImpl.class);

	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private SimpleMailMessage simpleMailMessage;
	private MimeMessage mimeMessage;

	@Override
	public void sendWithTemplate(MailBean mailBean) throws MailException {
		this.buildSimpleMailMessage(mailBean);
		String templateName = mailBean.getTemplateName();
		Map<String, Object> model = mailBean.getModel();
		String result = null;
		if (StringUtils.isBlank(templateName)) {
			log.debug("模板名称 IS NULL");
			throw new MailException("模板形式发送时，模板名称不能为空!");
		}
		if (model != null) {
			try {
				result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), templateName, "UTF-8", model);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new MailException(e.getMessage());
			}
		}
		simpleMailMessage.setText(result);
		mailSender.send(simpleMailMessage);
		this.display(simpleMailMessage);
	}

	@Override
	public void sendWithTemplateAndAttach(MailBean mailBean) throws MailException {
		mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			this.buildMimeMessage(mailBean, messageHelper);
			String templateName = mailBean.getTemplateName();
			Map<String, Object> model = mailBean.getModel();
			String filePath = mailBean.getFilePath();
			String result = null;
			if (StringUtils.isBlank(templateName)) {
				log.debug("模板名称 IS NULL");
				throw new MailException("模板形式发送时，模板名称不能为空!");
			}
			if (model != null) {
				result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), templateName, "UTF-8", model);
			}
			FileSystemResource file = new FileSystemResource(new File(filePath));
			messageHelper.addAttachment(MimeUtility.encodeWord(file.getFilename()), file);
			messageHelper.setText(result);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new MailException("模板带附件形式的邮件,发送失败 !");
		}
	}

	@Override
	public void sendText(MailBean mailBean) throws MailException {
		this.buildSimpleMailMessage(mailBean);
		mailSender.send(simpleMailMessage);
	}

	@Override
	public void sendHtml(MailBean mailBean) throws MailException, MessagingException {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setTo(mailBean.getTo());
		if (mailBean.getCc() != null) {
			if (StringUtils.isNotBlank(mailBean.getCc()[0])) {
				helper.setCc(mailBean.getCc());
			}
		}
		if (mailBean.getBcc() != null) {
			if (StringUtils.isNotBlank(mailBean.getBcc()[0])) {
				helper.setBcc(mailBean.getBcc());
			}
		}
		helper.setFrom(mailBean.getFrom());
		helper.setSubject(mailBean.getSubject());
		helper.setText(mailBean.getText(), true);
		mailSender.send(msg);
	}

	@Override
	public void sendWithAttachmentAndImage(MailBean mailBean) throws Exception {
		String filePath = mailBean.getFilePath();
		String imagePath = mailBean.getImagePath();
		if (StringUtils.isNotBlank(imagePath) && StringUtils.isNotBlank(filePath)) {
			this.sendWithAttachmentAndImage(mailBean, true, true);
		} else {
			throw new MailException("filePath And imagePath must not be null");
		}
	}

	@Override
	public void sendWithAttachment(MailBean mailBean) throws Exception {
		String filePath = mailBean.getFilePath();
		if (StringUtils.isNotBlank(filePath)) {
			this.sendWithAttachmentAndImage(mailBean, true, false);
		} else {
			throw new MailException("filePath must not be null");
		}
	}

	@Override
	public void sendWithImage(MailBean mailBean) throws Exception {
		String imagePath = mailBean.getImagePath();
		if (StringUtils.isNotBlank(imagePath)) {
			this.sendWithAttachmentAndImage(mailBean, false, true);
		} else {
			throw new MailException("imagePath must not be null");
		}
	}

	@Override
	public void sendWithTemplateHtml(MailBean mailBean) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		this.buildMimeMessage(mailBean, helper);
		String templateName = mailBean.getTemplateName();
		Map<String, Object> model = mailBean.getModel();
		String filePath = mailBean.getFilePath();
		String result = null;
		if (StringUtils.isBlank(templateName)) {
			log.debug("模板名称 IS NULL");
			throw new MailException("模板形式发送时，模板名称不能为空!");
		}
		if (model != null) {
			result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), templateName, "UTF-8", model);
		}
		if (StringUtils.isNotBlank(mailBean.getFilePath())) {
			FileSystemResource file = new FileSystemResource(new File(filePath));
			helper.addAttachment(MimeUtility.encodeWord(file.getFilename()), file);
		}
		if (!ArrayUtils.isEmpty(mailBean.getFileBeans())) {
			for (FileBean fb : mailBean.getFileBeans()) {
				FileSystemResource file = new FileSystemResource(new File(fb.getFilePath()));
				if (file.isReadable()) {
					helper.addAttachment(MimeUtility.encodeWord(fb.getFileName()), file);
				}
			}
		}
		if (!ArrayUtils.isEmpty(mailBean.getResourceBeans())) {
			for (ResourceBean rb : mailBean.getResourceBeans()) {
				helper.addAttachment(MimeUtility.encodeWord(rb.getFileName(), "utf-8", "Q"), rb.getSource());
			}
		}
		helper.setSubject(mailBean.getSubject());
		helper.setTo(mailBean.getTo());
		if (mailBean.getCc() != null) {
			helper.setTo(mailBean.getCc());
		}
		helper.setText(result, true);
		mailSender.send(msg);
	}

	/**
	 * 通过MailBean构建simpleMailMessage
	 * 
	 * @param mailBean
	 * @throws MailException
	 */
	private void buildSimpleMailMessage(MailBean mailBean) throws MailException {
		try {
			// 如果 from 为空，则使用默认 from 地址
			String defaultFrom = simpleMailMessage.getFrom();
			PropertyUtils.copyProperties(simpleMailMessage, mailBean);
			simpleMailMessage.setFrom(defaultFrom);
		} catch (Exception ex) {
			log.debug(ex.getMessage());
			throw new MailException("PropertyUtils.copyProperties exception ...");
		}
	}

	/**
	 * 通过MailBean构建MimeMessage
	 * 
	 * @param mailBean
	 * @param messageHelper
	 * @throws MailException
	 */
	private void buildMimeMessage(MailBean mailBean, MimeMessageHelper messageHelper) throws MailException {
		if (mailBean == null) {
			throw new MailException("mailBean must not be null");
		}
		try {
			String from = simpleMailMessage.getFrom();
			messageHelper.setFrom(from);
			if (mailBean.getTo() != null && mailBean.getTo().length > 0) {
				messageHelper.setTo(mailBean.getTo());
			}
			if (mailBean.getCc() != null && mailBean.getCc().length > 0) {
				messageHelper.setCc(mailBean.getCc());
			}
			if (mailBean.getBcc() != null && mailBean.getBcc().length > 0) {
				messageHelper.setBcc(mailBean.getBcc());
			}
			if (StringUtils.isNotBlank(mailBean.getSubject())) {
				messageHelper.setSubject(mailBean.getSubject());
			}
			if (StringUtils.isNotBlank(mailBean.getText())) {
				messageHelper.setText(mailBean.getText(), true);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new MailException(e);
		}
	}

	/**
	 * 发送带有附件的邮件
	 * 
	 * @param mailBean
	 * @param hasAttach
	 * @param hasImage
	 * @throws MailException
	 */
	private void sendWithAttachmentAndImage(MailBean mailBean, boolean hasAttach, boolean hasImage) throws MailException {
		mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			this.buildMimeMessage(mailBean, messageHelper);
			if (hasAttach) {
				String filePath = mailBean.getFilePath();
				FileSystemResource file = new FileSystemResource(new File(filePath));
				messageHelper.addAttachment(MimeUtility.encodeWord(file.getFilename()), file);
			}
			if (hasImage) {
				String imagePath = mailBean.getImagePath();
				String html = "<html><head></head><body><img src=\"cid:imageId\"/></body></html>";
				messageHelper.setText(mailBean.getText() + html, true);
				FileSystemResource image = new FileSystemResource(new File(imagePath));
				messageHelper.addInline("imageId", image);
			}
			System.out.println(mimeMessage.toString());
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new MailException(e.getMessage());
		}
	}

	/**
	 * 显示邮件内容
	 * 
	 * @param message
	 */
	public void display(SimpleMailMessage message) {
		System.out.println("邮件内容是:");
		System.out.println(message.toString());
	}

	/** Getter and setter */

	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @param velocityEngine
	 *            the velocityEngine to set
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * @param simpleMailMessage
	 *            the simpleMailMessage to set
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @return the velocityEngine
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	/**
	 * @return the simpleMailMessage
	 */
	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

}
