package com.uncodeframework.core.plugins.email;

import javax.mail.MessagingException;

/**
 * Mail Send Service Interface
 *
 */
public interface IMailSendService {

	/**
	 * 套用模板发送邮件
	 * 
	 * @param mailBean
	 * @throws MailException
	 */
	public abstract void sendWithTemplate(MailBean mailBean) throws MailException;

	/**
	 * 套用模板发送邮件，带有附件
	 * @param mailBean
	 * @throws MailException
	 */
	public abstract void sendWithTemplateAndAttach(MailBean mailBean) throws MailException;
	
	/**
	 * 发送普通文本文件
	 * @param mailBean
	 * @throws MailException
	 */
	public abstract void sendText(MailBean mailBean) throws MailException;
	
	/**
	 * 发送Html 格式的邮件
	 * @param mailBean
	 * @throws MailException
	 * @throws MessagingException 
	 */
	public abstract void sendHtml(MailBean mailBean) throws MailException, MessagingException;
	
	/**
	 * 发送普通文本文件，带有图片嵌套和附件
	 * @param mailBean
	 * @throws Exception
	 */
	public abstract void sendWithAttachmentAndImage(MailBean mailBean) throws Exception;
	
	/**
	 * 发送普通文本邮件，带有附件
	 * @param mailBean
	 * @throws Exception
	 */
	public abstract void sendWithAttachment(MailBean mailBean) throws Exception;
	
	/**
	 * 发送普通文本邮件，带有图片嵌套
	 * @param mailBean
	 * @throws Exception
	 */
	public abstract void sendWithImage(MailBean mailBean) throws Exception;
	
	/**
	 * 发送带模板的HTML
	 * @param mailBean
	 * @throws Exception
	 */
	public abstract void sendWithTemplateHtml(MailBean mailBean) throws Exception;
}
