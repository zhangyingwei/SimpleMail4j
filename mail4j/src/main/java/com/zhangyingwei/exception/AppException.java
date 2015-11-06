package com.zhangyingwei.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class AppException extends RuntimeException{
	
	public AppException(){
		super();
	}
	
	public AppException(String message){
		super(message);
	}
	
	public AppException(Throwable e){
		super(e);
	}
	
	public AppException(String message,Throwable e){
		super(message, e);
	}
}
