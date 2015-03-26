/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.model;

import java.util.Objects;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public class User {

    private int userId;
	private String user;
    private String userName;
    private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 61 * hash + this.userId;
		hash = 61 * hash + Objects.hashCode(this.userName);
		hash = 61 * hash + Objects.hashCode(this.password);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.userId != other.userId) {
			return false;
		}
		if (!Objects.equals(this.userName, other.userName)) {
			return false;
		}
		if (!Objects.equals(this.password, other.password)) {
			return false;
		}
		return true;
	}

}