package com.zhangyingwei.mail;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;

import com.zhangyingwei.handler.MailHandler;
import com.zhangyingwei.model.MailMessage;

public class MailLoginTest {
	public static void main(String[] args) throws Exception {
		String toaddress = "mail@mail.com";
//		String toaddress = "mail@mail.com";
		MailHandler maillogin = MailHandler.getInstence("mail@mail.com","password");
		maillogin.sendMessage(new MailMessage(maillogin.getMail()).setToAddress(toaddress).setSubject("这是一个封测试邮件，我是邮件的标题").addText("这是一个邮件内容").addExtra("G:\\jilege.doc").init());
	}
}
