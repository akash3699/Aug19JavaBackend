package com.app.controller;

import java.util.Date;

import com.app.pojos.Policies;
import com.app.pojos.PremiumFrequency;
import com.app.pojos.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerPolicyDetailsWithUserAndPolicy {

	
	
	private int userid;
	
	private int policyid;
	private Date buydate;
	private float premiumamout=0;
	private int paidpremiumcount;
	private int totalpremiumcount;
	private PremiumFrequency ps;
	
	public CustomerPolicyDetailsWithUserAndPolicy() {
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPolicyid() {
		return policyid;
	}

	public void setPolicyid(int policyid) {
		this.policyid = policyid;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public float getPremiumamout() {
		return premiumamout;
	}

	public void setPremiumamout(float premiumamout) {
		this.premiumamout = premiumamout;
	}

	public int getPaidpremiumcount() {
		return paidpremiumcount;
	}

	public void setPaidpremiumcount(int paidpremiumcount) {
		this.paidpremiumcount = paidpremiumcount;
	}

	public int getTotalpremiumcount() {
		return totalpremiumcount;
	}

	public void setTotalpremiumcount(int totalpremiumcount) {
		this.totalpremiumcount = totalpremiumcount;
	}

	public PremiumFrequency getPs() {
		return ps;
	}

	public void setPs(PremiumFrequency ps) {
		this.ps = ps;
	}

	@Override
	public String toString() {
		return "CustomerPolicyDetailsWithUserAndPolicy [userid=" + userid + ", policyid=" + policyid + ", buydate="
				+ buydate + ", premiumamout=" + premiumamout + ", paidpremiumcount=" + paidpremiumcount
				+ ", totalpremiumcount=" + totalpremiumcount + ", ps=" + ps + "]";
	}
	
}
