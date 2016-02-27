/**
 * 
 */
package com.zhangyingwei.simplemail.model;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.zhangyingwei.simplemail.exception.MailAppException;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class AbstractMail {
	private String fromAddress;
	private String password;
	private String toAddress;
	private Properties properties;
	public static final String MAIL_TRANSPORT_PROTOCOL_SMTP = "smtp";
	public String getFromAddress() throws MailAppException {
		if(fromAddress==null){
			throw new MailAppException("fromAddress is empty");
		}
		return fromAddress;
	}
	protected void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	public String getToAddress() {
		return toAddress;
	}
	protected void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public Properties getProperties() throws MailAppException {
		Properties properties = new Properties();
		properties.setProperty("mail.host", this.getHost());
		properties.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL_SMTP);
		properties.setProperty("mail.smtp.auth", "true");
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	/**
	 * 根据username获取邮箱host
	 * @return
	 * @throws MailAppException 
	 */
	protected String getHost() throws MailAppException{
		return new StringBuffer("smtp.").append(this.getFromAddress().split("@")[1]).toString();
	}
	public Session getSession() throws MailAppException {
		Session session = Session.getDefaultInstance(this.getProperties(), 
			    new javax.mail.Authenticator(){
			        protected PasswordAuthentication getPasswordAuthentication() {
			        	PasswordAuthentication passwordAuthentication = null;
			            try {
			            	passwordAuthentication =  new PasswordAuthentication(
							    getFromAddress(), getPassword());
						} catch (MailAppException e) {
							e.printStackTrace();
						}
			            return passwordAuthentication;
			        }
			});
		session.setDebug(true);
		return session;
	}
}
