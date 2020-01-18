package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.Nullable;

@Entity
public class CustomerPolicyDetails {
	private Integer cpid;
	@JsonIgnore
	private User userid;
	@JsonIgnore
	private Policies policyid;
	private Date buydate;
	private float premiumamout=0;
	private int paidpremiumcount;
	private int totalpremiumcount;
	private PremiumFrequency ps;
	private List<PremiumSchedule> premiumschedule= new ArrayList<PremiumSchedule>();
	
	public CustomerPolicyDetails() {
		// TODO Auto-generated constructor stub
	}
	public CustomerPolicyDetails(Date buydate, float premiumamout, int paidpremiumcount, int totalpremiumcount) {
		super();
		this.buydate = buydate;
		this.premiumamout = premiumamout;
		this.paidpremiumcount = paidpremiumcount;
		this.totalpremiumcount = totalpremiumcount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	
	@OneToMany(targetEntity = PremiumSchedule.class,mappedBy = "cp1",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	public List<PremiumSchedule> getPremiumschedule() {
		return premiumschedule;
	}
	public void setPremiumschedule(List<PremiumSchedule> premiumschedule) {
		this.premiumschedule = premiumschedule;
	}
	
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="Userid")
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="PolicyId")
	public Policies getPolicyid() {
		return policyid;
	}
	public void setPolicyid(Policies policyid) {
		this.policyid = policyid;
	}
	
	@Temporal(TemporalType.DATE)
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
	
	
	@Enumerated(EnumType.STRING)
	public PremiumFrequency getPs() {
		return ps;
	}
	public void setPs(PremiumFrequency ps) {
		this.ps = ps;
	}
	@Override
	public String toString() {
		return "CustomerPolicyDetails [userid=" + userid + ", policyid=" + policyid + ", buydate=" + buydate
				+ ", premiumamout=" + premiumamout + ", paidpremiumcount=" + paidpremiumcount + ", totalpremiumcount="
				+ totalpremiumcount + "]";
	}
	
	

}
