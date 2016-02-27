/**
 * Mail对象
 */
package com.zhangyingwei.simplemail.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.zhangyingwei.simplemail.exception.MailAppException;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class Mail extends AbstractMail{
	
	private Transport transport;
	private MimeMessage message;
	
	/**
	 * 获取连接 connection
	 * @return
	 * @throws NoSuchProviderException
	 * @throws MailAppException 
	 */
	public void connent(String username,String password) throws NoSuchProviderException, MailAppException{
		this.setFromAddress(username);
		this.setPassword(password);
		this.transport = this.getSession().getTransport();
		try {
			transport.connect(this.getHost(),this.getFromAddress(),this.getPassword());
		} catch (MessagingException e) {
			throw new MailAppException("connect err", e);
		}
	}
	/**
	 * 发送
	 * @param toAddress
	 * @param mailMessage
	 * @throws MessagingException
	 * @throws MailAppException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void send(String toAddress,MailMessage mailMessage) throws MessagingException, MailAppException, FileNotFoundException, IOException{
		this.setToAddress(toAddress);
		this.buildMimeMessage(mailMessage);
		this.transport.sendMessage(this.message,this.message.getAllRecipients());
	}
	/**
	 * 发送
	 * @param toAddress
	 * @param subject
	 * @throws MessagingException
	 * @throws MailAppException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void send(String toAddress,String subject) throws MessagingException, MailAppException, FileNotFoundException, IOException{
		this.setToAddress(toAddress);
		MailMessage mailMessage = new MailMessage(subject,"");
		this.buildMimeMessage(mailMessage);
		this.transport.send(this.message,this.message.getAllRecipients());
	}
	/**
	 * 发送
	 * @param toAddress
	 * @param subject
	 * @param content
	 * @throws MessagingException
	 * @throws MailAppException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void send(String toAddress,String subject,String content) throws MessagingException, MailAppException, FileNotFoundException, IOException{
		this.setToAddress(toAddress);
		MailMessage mailMessage = new MailMessage(subject,content);
		this.buildMimeMessage(mailMessage);
		this.transport.send(this.message,this.message.getAllRecipients());
	}
	/**
	 * 发送
	 * @param toAddress
	 * @param subject
	 * @param content
	 * @param urls
	 * @throws MessagingException
	 * @throws MailAppException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void send(String toAddress,String subject,String content,String[] urls) throws MessagingException, MailAppException, FileNotFoundException, IOException{
		this.setToAddress(toAddress);
		MailMessage mailMessage = new MailMessage(subject,content,urls);
		this.buildMimeMessage(mailMessage);
		this.transport.send(this.message,this.message.getAllRecipients());
	}
	
	/**
	 * 构建MimeMessage对象
	 * @throws MailAppException
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void buildMimeMessage(MailMessage mailMessage) throws MailAppException, AddressException, MessagingException, FileNotFoundException, IOException{
		this.message = new MimeMessage(super.getSession());
		this.message.setFrom(new InternetAddress(super.getFromAddress()));
		this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(super.getToAddress()));
		this.message.setSubject(mailMessage.getSubject());
		this.message.setContent(mailMessage.buildMimeMultipart());
		this.message.saveChanges();
		this.message.writeTo(new FileOutputStream(getMailFileName(mailMessage)));
	}
	/**
	 * 生成文件名称
	 * @param mailMessage
	 * @return
	 */
	private String getMailFileName(MailMessage mailMessage){
		String dateStr = new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date());
		return dateStr.concat(mailMessage.getSubject()).concat(".eml");
	}
}
