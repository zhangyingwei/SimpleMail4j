# SimpleMail4j

一个java写的发送邮件的例子，并进行了一定的封装。使得发送邮件简单化。

#Demo

```java
String toaddress = "yourtoaddress";
MailHandler maillogin = MailHandler.getInstence("fromaddress@aaa.com","password");
maillogin.sendMessage(new MailMessage(maillogin.getMail()).setToAddress(toaddress).setSubject("这是一个封测试邮件，我是邮件的标题").addText("这是一个邮件内容").addExtra("G:\\jilege.doc").init());
```

这里主要涉及到三个对象
* MailHandler
主要负责邮件的发送
* MailMessage
邮件消息对象
* Mail
邮件对象