package com.uncodeframework.core.plugins.email;

import java.util.Date;
import java.util.Map;

/**
 * Mail Bean
 *
 */
public class MailBean {

	/** * @Fileds from : 邮件发送 from 地址，非必填，有默认值 */
	private String from;
	/** * @Fileds to : 必填 邮件发送 to 地址数组 */
	private String[] to;
	/** * @Fileds cc : 邮件发送 cc 地址数组 */
	private String[] cc;
	/** * @Fileds bcc : 邮件发送 bcc 地址数组 */
	private String[] bcc;
	/** * @Fileds sentDate : 发送时间 */
	private Date sentDate;
	/** * @Fileds subject : 必填 邮件主题 */
	private String subject;
	/** * @Fileds text : 邮件内容 */
	private String text;
	/** * @Fileds filePath : 只有发送带有附件发送的邮件需指定参数 */
	private String filePath;
	/** * @Fileds imagePath : 只有发送带有图片嵌入的邮件需制定止参数 */
	private String imagePath;
	/** * @Fileds model : 用邮件模板发送时(MailSenderService.sendWithTemplage)需要制定止参数 */
	private Map<String, Object> model;
	/** * @Fileds templateName : 用邮件模板发送时(MailSenderService.sendWithTemplage)需要制定止参数 */
	private String templateName;
	/** * @Fileds fileBeans :  */
	private FileBean[] fileBeans;
	/** * @Fileds resourceBeans :  */
	private ResourceBean[] resourceBeans;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public FileBean[] getFileBeans() {
		return fileBeans;
	}

	public void setFileBeans(FileBean[] fileBeans) {
		this.fileBeans = fileBeans;
	}

	public ResourceBean[] getResourceBeans() {
		return resourceBeans;
	}

	public void setResourceBeans(ResourceBean[] resourceBeans) {
		this.resourceBeans = resourceBeans;
	}
}
