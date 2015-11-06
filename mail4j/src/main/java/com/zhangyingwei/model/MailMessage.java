package com.zhangyingwei.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.text.html.MinimalHTMLWriter;

import com.zhangyingwei.exception.AppException;

public class MailMessage {
	MimeMessage message ;
	
	List<MimeBodyPart> texts = new ArrayList<MimeBodyPart>();
	List<MimeBodyPart> extras = new ArrayList<MimeBodyPart>();
	
	private String toAddress;
	public MailMessage setToAddress(String toAddress) {
		try {
			this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		} catch (AddressException e) {
			System.out.println(e);
		} catch (MessagingException e) {
			System.out.println(e);
		}
		return this;
	}
	public MailMessage(Mail mail){
		this.message = new MimeMessage(mail.getSession());
		try {
			this.message.setFrom(new InternetAddress(mail.getUsername()));
		} catch (AddressException e) {
			System.out.println(e);
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 设置邮件主题
	 * @param subject
	 * @return
	 */
	public MailMessage setSubject(String subject){
		try {
			this.message.setSubject(subject);
		} catch (MessagingException e) {
			throw new AppException("设置邮件主题错误", e);
		}
		return this;
	}
	
	public Message getMessage(){
		return this.message;
	}
	
	/**
	 * 添加文字内容
	 * @param text
	 * @return
	 */
	public MailMessage addText(String text){
		MimeBodyPart bodyPart = new MimeBodyPart();
		try {
			bodyPart.setContent(text, "text/html;charset=UTF-8");
		} catch (MessagingException e) {
			throw new AppException("添加text错误",e);
		}
		this.texts.add(bodyPart);
		return this;
	}
	
	/**
	 * 添加文中图片内容
	 * @param url
	 * @return
	 */
	public MailMessage addExtra(String url){
		File file = new File(url);
		MimeBodyPart extra = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource(url));
		try {
			extra.setDataHandler(dh);
			System.out.println("@:"+file.getName());
			extra.setFileName(file.getName());
		} catch (MessagingException e) {
			throw new AppException("添加extra错误",e);
		}
		this.extras.add(extra);
		return this;
	}
	
	/**
	 * 构建MimeMultipart
	 * @return
	 */
	public MimeMultipart buildMimeMultipart(){
		MimeMultipart multipart = new MimeMultipart();
		try {
			if(this.texts.size()!=0){
				for(MimeBodyPart m:this.texts){
					multipart.addBodyPart(m);
				}
			}
			if(this.extras.size()!=0){
				for(MimeBodyPart m:this.extras){
					multipart.addBodyPart(m);
				}
			}
		} catch (Exception e) {
			throw new AppException("~~~~(>_<)~~~~");
		}
		return multipart;
	}
	
	public MailMessage init(){
		try {
			this.message.setContent(this.buildMimeMultipart());
			this.message.saveChanges();
			this.message.writeTo(new FileOutputStream("mailfile.eml"));
		} catch (MessagingException e) {
			throw new AppException("设置邮件内容出错",e);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return this;
	}
}
