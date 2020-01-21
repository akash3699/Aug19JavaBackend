package com.app.dao;

import java.util.List;

import com.app.pojos.ClaimTracker;
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
	public List<CustomerPolicyDetails> getUserPolicyDetails(int userid);
	public int forgotUserPassword(String email);
	public User getUserByEmail(String email);
	public List<CustomerPolicyDetails> getAllUserPolicyDetails();
	public List<ClaimTracker> getClaimTrackerDetails(int userid);
	public Integer AddClaimTrackerDetails(ClaimTracker ct ,int userid);
	public ClaimTracker getClaimTrackerDetailsByCTid(int claimtrackerid);
	public Integer UpdateClaimTrackerDetailsByCTid(ClaimTracker ct, int claimtrackerid);
	public List<ClaimTracker> getAllClaimTrackerDetails();
}
