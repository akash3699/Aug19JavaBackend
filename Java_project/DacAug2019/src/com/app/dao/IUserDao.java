package com.app.dao;

import com.app.pojos.CustomerPolicyDetails;
import com.app.pojos.Policies;
import com.app.pojos.User;

public interface IUserDao {

	public Integer registerUser(User u);
	public User loginUser(User u);
	public User getUserDetails(int userid);
	public int updateUserDetails(User user);
	public int updateUserPasswd(int userId, User user);
	public int buyUserPolicy(CustomerPolicyDetails cpdetails,User u,Policies p);
}
