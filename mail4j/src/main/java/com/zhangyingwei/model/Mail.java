package com.zhangyingwei.model;

import javax.mail.Session;

public class Mail {
	private Session session;
	private String username;
	private String password;
	public Session getSession() {
		return session;
	}
	public Mail setSession(Session session) {
		this.session = session;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public Mail setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Mail setPassword(String password) {
		this.password = password;
		return this;
	}
}
