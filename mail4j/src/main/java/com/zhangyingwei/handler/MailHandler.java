package com.zhangyingwei.handler;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import com.zhangyingwei.exception.AppException;
import com.zhangyingwei.model.Mail;
import com.zhangyingwei.model.MailMessage;

public class MailHandler {
	
	

	private String username;
	private String password;
	
	private static class MailLoginHandler{
		private static MailHandler mailLogin = new MailHandler();
	}
	/**
	 * 单例邮件登录器
	 * @return
	 */
	public static MailHandler getInstence(String username,String password){
		return MailLoginHandler.mailLogin.init(username, password);
	}
	
	/**
	 * 初始化邮箱 密码信息
	 * @param username
	 * @param password
	 * @return
	 */
	private MailHandler init(String username,String password){
		UserNameAndPasswordValibal(username,password);
		this.username = username;
		this.password = password;
		return this;
	}
	
	/**
	 * 获取连接 connection
	 * @return
	 * @throws NoSuchProviderException
	 */
	private Transport connent() throws NoSuchProviderException{
		Session session = this.getCurrentSession();
		Transport transport = session.getTransport();
		try {
			transport.connect(this.getHost(),username,password);
			return transport;
		} catch (MessagingException e) {
			throw new AppException("连接失败", e);
		}
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public Session getCurrentSession(){
		Session session = Session.getInstance(this.getProperties());
		session.setDebug(true);
		return session;
	}
	
	/**
	 * 获取Properties
	 * @return
	 */
	private Properties getProperties(){
		Properties properties = new Properties();
		properties.setProperty("mail.host", this.getHost());
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		System.out.println(properties);
		return properties;
	}
	
	/**
	 * 根据username获取邮箱host
	 * @return
	 */
	private String getHost(){
		return new StringBuffer("smtp.").append(this.username.split("@")[1]).toString();
	}
	
	/**
	 * 邮箱 密码 格式验证
	 * @param username
	 * @param password
	 */
	private void UserNameAndPasswordValibal(String username,String password){
		String regex = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
		if(username==null||username.equals("")||username.trim().length()==0){
			throw new AppException("邮箱不可以为空");
		}
		if(password==null||password.equals("")||password.trim().length()==0){
			throw new AppException("密码不可以为空");
		}
		if(!username.matches(regex)){
			throw new AppException("邮箱格式不正确");
		}
	}
	
	/**
	 * 发送邮件
	 * @throws MessagingException 
	 * @throws NoSuchProviderException 
	 */
	public void sendMessage(MailMessage messaeg) throws NoSuchProviderException, MessagingException{
		this.connent().sendMessage(messaeg.getMessage(), messaeg.getMessage().getAllRecipients());
	}
	
	/**
	 * 获取一个mail对象
	 * 主要是为了往message对象里传递session与username对象
	 * @return
	 */
	public Mail getMail() {
		return new Mail().setSession(this.getCurrentSession()).setUsername(this.username).setPassword(password);
	}
}
