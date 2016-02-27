/**
 * 
 */
package com.zhangyingwei.simplemail.handler.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import com.zhangyingwei.simplemail.exception.MailAppException;
import com.zhangyingwei.simplemail.handler.MailHandler;
import com.zhangyingwei.simplemail.model.Mail;
import com.zhangyingwei.simplemail.model.MailMessage;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailHandlerImpl implements MailHandler{
	
	private MailHandlerImpl(){}
	
	private Mail mail;
	
	/**
	 * MailHandlerImpl单例
	 * @author zhangyw
	 * @creation 2016-2-27
	 */
	private static class MailHandlerImpHandler{
		private static MailHandlerImpl instance = new MailHandlerImpl();
	}
	public static MailHandlerImpl getInstance(){
		return MailHandlerImpHandler.instance;
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#login(java.lang.String, java.lang.String)
	 */
	public MailHandler login(String username, String password) throws MailAppException{
		try {
			this.mail = new Mail();
			mail.connent(username, password);
		} catch (Exception e) {
			throw new MailAppException("login err",e);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String)
	 */
	public void send(String toAddress,MailMessage mailMessage) throws MessagingException, FileNotFoundException, MailAppException, IOException {
		mail.send(toAddress,mailMessage);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String)
	 */
	public void send(String toAddress, String subject) throws Exception {
		mail.send(toAddress, subject);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void send(String toAddress, String subject, String content)
			throws Exception {
		mail.send(toAddress, subject, content);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void send(String toAddress, String subject, String content,
			String[] urls) throws Exception {
		mail.send(toAddress, subject, content, urls);
	}

}
