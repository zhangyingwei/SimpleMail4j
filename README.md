# SimpleMail4j
> 一个基于com.sun.mail封装的mail4j工具包

##example
```
String[] urls = {"222.jpg","222.jpg","222.jpg"};//附件
MailHandler mailHandler = MailHandlerProxy.getInstance();
mailHandler.login("fromaddress@xxx.com", "********");
mailHandler.send("toaddress@xxx.com", "邮件主题","邮件内容",urls);
```

##主要方法
* MailHandlerProxy
	* public static MailHandlerProxy getInstance(); //获取MailHandlerProxy实例
	* public MailHandler login(String username,String password) throws Exception;//登录
	* public void send(String toAddress,MailMessage mailMessage) throws Exception; //发送邮件信息,包含一个邮件信息对象
	* public void send(String toAddress,String subject) throws Exception;//发送一个主题为subject的邮件，内容默认为空
	* public void send(String toAddress,String subject,String content) throws Exception;//发送一个主题为subject的邮件，内容为content的邮件
	* public void send(String toAddress,String subject,String content,String[] urls) throws Exception;//发送一个主题为subject的邮件，内容为content,附件列表为urls的邮件

* MailMessage
	* public MailMessage setSubject(String subject)；//设置邮件信息主题
	* public String getSubject();//获取邮件信息主题
	* public MailMessage addContent(String content);//添加邮件内容
	* public MailMessage addExtra(String extraUrl);//添加邮件附件
