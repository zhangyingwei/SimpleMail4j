package com.zhangyingwei.mail;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;

import com.zhangyingwei.handler.MailHandler;
import com.zhangyingwei.model.MailMessage;

public class MailLoginTest {
	public static void main(String[] args) throws Exception {
		String toaddress = "zhangyw_001@126.com";
//		String toaddress = "1170404227@qq.com";
		MailHandler maillogin = MailHandler.getInstence("zhangyw_001@163.com","723129bao");
		maillogin.sendMessage(new MailMessage(maillogin.getMail()).setToAddress(toaddress).setSubject("����һ��������ʼ��������ʼ��ı���").addText("����һ���ʼ�����").addExtra("G:\\jilege.doc").init());
	}
}
