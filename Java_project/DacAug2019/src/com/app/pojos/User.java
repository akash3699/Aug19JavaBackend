package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(
		   
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "mobile"})}
		)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String email;
	private String mobile;
	private String passwd;
	
	
	public User(Integer userId, String email, String mobile, String passwd) {
		super();
		this.userId = userId;
		this.email = email;
		this.mobile = mobile;
		this.passwd = passwd;
	}
	@Column(unique = true)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public User() {
		System.out.println("in user pojo class");
	}
	
	public User(String email) {
		super();
		
		this.email = email;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", mobile=" + mobile + ", passwd=" + passwd + "]";
	}
	
	
	

}
