package com.app.dao;

import java.util.List;

import com.app.pojos.CustomerPolicyDetails;
import com.app.pojos.HistoryOfPaidPremium;
import com.app.pojos.PremiumSchedule;

public interface ICustPolicyDao {

	public PremiumSchedule getNextPremiumSchedule(int custpolicyid);
	public PremiumSchedule payNextPremiumSchedule(int custpolicyid);
	public CustomerPolicyDetails getCustPolicyDetails(int cpid);
	public CustomerPolicyDetails getCustPolicyDetailsByPremiumSchedule(int premiumscheduleid);
	public PremiumSchedule getNextPremiumScheduleByCustPolicyId(int custid);
	public List<CustomerPolicyDetails> getUserPolicyDetailsWithPaidPremium(int userid);
	public HistoryOfPaidPremium getPaidDateByPremiumSchedule(int psid);
}
