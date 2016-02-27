/**
 * mail exception
 */
package com.zhangyingwei.simplemail.exception;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailAppException extends Exception{
	private static final long serialVersionUID = 1L;
	public MailAppException(){
		super();
	}
	public MailAppException(String message){
		super(message);
	}
	public MailAppException(String message,Exception e){
		super(message,e);
	}
}
