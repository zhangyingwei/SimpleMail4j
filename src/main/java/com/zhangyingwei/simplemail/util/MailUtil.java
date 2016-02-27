/**
 * 
 */
package com.zhangyingwei.simplemail.util;

/**
 * @author zhangyw
 * @creation 2016-2-27
 */
public class MailUtil {
	public static boolean isMailAddress(String address){
		if(address==null){
			return false;
		}
		String regex = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
		return address.matches(regex);
	}
}
