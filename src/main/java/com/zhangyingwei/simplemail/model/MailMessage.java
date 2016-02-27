/**
 * Mail Message
 */
package com.zhangyingwei.simplemail.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.zhangyingwei.simplemail.exception.MailAppException;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailMessage {
	/**
	 * 邮件主题
	 */
	private String subject;
	List<MimeBodyPart> content = new ArrayList<MimeBodyPart>();
	List<MimeBodyPart> extras = new ArrayList<MimeBodyPart>();
	
	public String getSubject() {
		return subject;
	}
	public MailMessage setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	/**
	 * 构造器
	 * @param subject
	 */
	public MailMessage(String subject){
		this.subject = subject;
	}
	/**
	 * 构造器
	 * @param subject
	 * @param content
	 * @throws MailAppException
	 */
	public MailMessage(String subject,String content) throws MailAppException{
		this.subject = subject;
		this.addContent(content);
	}
	/**
	 * 构造器
	 * @param subject
	 * @param content
	 * @param url
	 * @throws MailAppException
	 */
	public MailMessage(String subject,String content,String url) throws MailAppException{
		this.subject = subject;
		this.addContent(content);
		this.addExtra(url);
	}
	/**
	 * 构造器
	 * @param subject
	 * @param content
	 * @param urls
	 * @throws MailAppException
	 */
	public MailMessage(String subject,String content,String[] urls) throws MailAppException{
		this.subject = subject;
		this.addContent(content);
		for(String url:urls){
			this.addExtra(url);
		}
	}
	
	
	public MailMessage addContent(String content) throws MailAppException{
		MimeBodyPart bodyPart = new MimeBodyPart();
		try {
			bodyPart.setContent(content, "text/html;charset=UTF-8");
		} catch (MessagingException e) {
			throw new MailAppException("add content err",e);
		}
		this.content.add(bodyPart);
		return this;
	}
	
	public MailMessage addExtra(String extraUrl) throws MailAppException{
		File file = new File(extraUrl);
		MimeBodyPart extra = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource(extraUrl));
		try {
			extra.setDataHandler(dh);
			extra.setFileName(file.getName());
		} catch (MessagingException e) {
			throw new MailAppException("add extra err",e);
		}
		this.extras.add(extra);
		return this;
	}
	
	/**
	 * 构建MimeMultipart
	 * @return
	 * @throws MailAppException 
	 */
	public MimeMultipart buildMimeMultipart() throws MailAppException{
		MimeMultipart multipart = new MimeMultipart();
		try {
			if(this.content.size()!=0){
				for(MimeBodyPart m:this.content){
					multipart.addBodyPart(m);
				}
			}
			if(this.extras.size()!=0){
				for(MimeBodyPart m:this.extras){
					multipart.addBodyPart(m);
				}
			}
		} catch (Exception e) {
			throw new MailAppException("~~~~(>_<)~~~~");
		}
		return multipart;
	}
	
}
