/**
 * 
 */
package com.zhangyingwei.simplemail.handler.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.mail.MessagingException;

import com.zhangyingwei.simplemail.exception.MailAppException;
import com.zhangyingwei.simplemail.handler.MailHandler;
import com.zhangyingwei.simplemail.model.MailMessage;
import com.zhangyingwei.simplemail.util.MailUtil;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailHandlerProxy implements MailHandler{
	
	private MailHandlerImpl mailHandler = MailHandlerImpl.getInstance();

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#login(java.lang.String, java.lang.String)
	 */
	public MailHandler login(String username, String password) throws MailAppException{
		if(!this.mailValidation(username)){
			throw new MailAppException("username is not valid email address");
		}
		if(!this.strValidation(password)){
			throw new MailAppException("password is empty");
		}
		mailHandler.login(username, password);
		return this;
	}
	
	/**
	 * 字符串合法性验证
	 * @param msg
	 * @return
	 */
	private boolean strValidation(String msg){
		if(msg.trim()==null||msg.trim().length()==0){
			return false;
		}
		return true;
	}
	/**
	 * 邮箱地址合法性验证
	 * @param address
	 * @return
	 */
	private boolean mailValidation(String address){
		return MailUtil.isMailAddress(address);
	}
	/**
	 * 数组合法性验证
	 * @param urls
	 * @return
	 */
	private boolean arrValidation(String[] urls){
		return urls!=null&&urls.length>0;
	}
	
	
	/**
	 * 代理类单例
	 * @author zhangyw
	 * @creation 2016-2-27
	 */
	private static class MailHandlerProxyHandler{
		private static MailHandlerProxy instance = new MailHandlerProxy();
	}
	public static MailHandlerProxy getInstance(){
		return MailHandlerProxyHandler.instance;
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String)
	 */
	public void send(String toAddress,MailMessage mailMessage) throws MailAppException, MessagingException, FileNotFoundException, IOException{
		if(!this.mailValidation(toAddress)){
			throw new MailAppException("toaddress is not valid mail address");
		}
		if(!this.strValidation(mailMessage.getSubject())){
			throw new MailAppException("subject in mailMessage is empty");
		}
		this.mailHandler.send(toAddress,mailMessage);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String)
	 */
	public void send(String toAddress, String subject) throws Exception {
		if(!this.mailValidation(toAddress)){
			throw new MailAppException("toaddress is not valid mail address");
		}
		if(!this.strValidation(subject)){
			throw new MailAppException("subject is empty");
		}
		this.mailHandler.send(toAddress, subject);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void send(String toAddress, String subject, String content)throws Exception {
		if(!this.mailValidation(toAddress)){
			throw new MailAppException("toaddress is not valid mail address");
		}
		if(!this.strValidation(subject)){
			throw new MailAppException("subject is empty");
		}
		if(!this.strValidation(content)){
			throw new MailAppException("content is empty");
		}
		this.mailHandler.send(toAddress, subject, content);
	}

	/* (non-Javadoc)
	 * @see com.zhangyingwei.simplemail.handler.MailHandler#send(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void send(String toAddress, String subject, String content,String[] urls) throws Exception {
		if(!this.mailValidation(toAddress)){
			throw new MailAppException("toaddress is not valid mail address");
		}
		if(!this.strValidation(subject)){
			throw new MailAppException("subject is empty");
		}
		if(!this.strValidation(content)){
			throw new MailAppException("content is empty");
		}
		if(!this.arrValidation(urls)){
			throw new MailAppException("urls is empty");
		}
		this.mailHandler.send(toAddress, subject, content, urls);
	}
}
