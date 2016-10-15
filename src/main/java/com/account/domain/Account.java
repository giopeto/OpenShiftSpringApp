package com.account.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accounts")
public class Account {
	@Id
	private String id;

	private String email;
	private String userName;
	private String password;
	private String role = "ROLE_USER";

	public Account () {}

	public Account(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Account(String id, String userName,
				String password, String email, String role) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
