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
 
 /**
 * @ClassName: Sendmail
 * @Description: ����Email
 * @author: �°�����
 * @date: 2015-1-12 ����9:42:56
 *
 */ 
 public class SendmailWithImg {
 
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
         //3�������ʼ�����������Ҫ�������ṩ������û��������������֤
         ts.connect("smtp.sohu.com", "gacl", "��������");
         //4�������ʼ�
         Message message = createImageMail(session);
         //5�������ʼ�
         ts.sendMessage(message, message.getAllRecipients());
         ts.close();
     }
     
     /**
     * @Method: createImageMail
     * @Description: ����һ���ʼ����Ĵ�ͼƬ���ʼ�
     * @Anthor:�°�����
     *
     * @param session
     * @return
     * @throws Exception
     */ 
     public static MimeMessage createImageMail(Session session) throws Exception {
         //�����ʼ�
         MimeMessage message = new MimeMessage(session);
         // �����ʼ��Ļ�����Ϣ
         //������
         message.setFrom(new InternetAddress("gacl@sohu.com"));
         //�ռ���
         message.setRecipient(Message.RecipientType.TO, new InternetAddress("xdp_gacl@sina.cn"));
         //�ʼ�����
         message.setSubject("��ͼƬ���ʼ�");
 
         // ׼���ʼ�����
         // ׼���ʼ���������
         MimeBodyPart text = new MimeBodyPart();
         text.setContent("����һ���ʼ����Ĵ�ͼƬ<img src='cid:xxx.jpg'>���ʼ�", "text/html;charset=UTF-8");
         // ׼��ͼƬ����
         MimeBodyPart image = new MimeBodyPart();
         DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
         image.setDataHandler(dh);
         image.setContentID("xxx.jpg");
         // �������ݹ�ϵ
         MimeMultipart mm = new MimeMultipart();
         mm.addBodyPart(text);
         mm.addBodyPart(image);
         mm.setSubType("related");
 
         message.setContent(mm);
         message.saveChanges();
         //�������õ��ʼ�д�뵽E�����ļ�����ʽ���б���
         message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
         //���ش����õ��ʼ�
         return message;
     }
 }