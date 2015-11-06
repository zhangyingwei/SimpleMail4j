package com.zhangyingwei.handler;

import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
* @ClassName: Sendmail
* @Description: ����Email
* @author: �°�����
* @date: 2015-1-12 ����9:42:56
*
*/ 
public class SendmailWithInnerImgAndFJ {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //ʹ��JavaMail�����ʼ���5������
        //1������session
        Session session = Session.getInstance(prop);
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();
        //3�������ʼ�������
        ts.connect("smtp.sohu.com", "gacl", "��������");
        //4�������ʼ�
        Message message = createMixedMail(session);
        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    
    /**
    * @Method: createMixedMail
    * @Description: ����һ��������ʹ�ͼƬ���ʼ�
    * @Anthor:�°�����
    *
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createMixedMail(Session session) throws Exception {
        //�����ʼ�
        MimeMessage message = new MimeMessage(session);
        
        //�����ʼ��Ļ�����Ϣ
        message.setFrom(new InternetAddress("gacl@sohu.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
        message.setSubject("�������ʹ�ͼƬ�ĵ��ʼ�");
        
        //����
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("xxx����Ů��xxxx<br/><img src='cid:aaa.jpg'>","text/html;charset=UTF-8");
        
        //ͼƬ
        MimeBodyPart image = new MimeBodyPart();
        image.setDataHandler(new DataHandler(new FileDataSource("src\\3.jpg")));
        image.setContentID("aaa.jpg");
        
        //����1
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\4.zip"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());
        
        //����2
        MimeBodyPart attach2 = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("src\\����.zip"));
        attach2.setDataHandler(dh2);
        attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
        
        //������ϵ:���ĺ�ͼƬ
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        mp1.setSubType("related");
        
        //������ϵ:���ĺ͸���
        MimeMultipart mp2 = new MimeMultipart();
        mp2.addBodyPart(attach);
        mp2.addBodyPart(attach2);
        
        //�������ĵ�bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
        mp2.addBodyPart(content);
        mp2.setSubType("mixed");
        
        message.setContent(mp2);
        message.saveChanges();
        
        message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
        //���ش����õĵ��ʼ�
        return message;
    }
}