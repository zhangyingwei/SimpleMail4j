/**
 * 
 */
package com.zhangyingwei.simplemail.handler;

import com.zhangyingwei.simplemail.exception.MailAppException;
import com.zhangyingwei.simplemail.model.MailMessage;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public interface MailHandler {
	public MailHandler login(String username,String password) throws Exception;
	public void send(String toAddress,MailMessage mailMessage) throws Exception;
	public void send(String toAddress,String subject) throws Exception;
	public void send(String toAddress,String subject,String content) throws Exception;
	public void send(String toAddress,String subject,String content,String[] urls) throws Exception;
}
