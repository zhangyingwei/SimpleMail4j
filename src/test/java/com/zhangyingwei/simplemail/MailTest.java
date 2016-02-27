/**
 * 
 */
package com.zhangyingwei.simplemail;

import java.io.File;

import com.zhangyingwei.simplemail.handler.MailHandler;
import com.zhangyingwei.simplemail.handler.impl.MailHandlerProxy;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailTest {
	public static void main(String[] args) throws Exception {
		String[] urls = {"222.jpg","222.jpg","222.jpg"};//附件
		MailHandler mailHandler = MailHandlerProxy.getInstance();
		mailHandler.login("fromaddress@xxx.com", "********");
		mailHandler.send("toaddress@xxx.com", "邮件主题","邮件内容",urls);
	}
}
