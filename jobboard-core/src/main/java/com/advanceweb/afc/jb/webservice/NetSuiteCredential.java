package com.advanceweb.afc.jb.webservice;

import org.springframework.stereotype.Component;

@Component("netSuiteCredential")
public class NetSuiteCredential {

	
	private String account;
	
	private String email;
	
	private String password;
	
	private String role;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
