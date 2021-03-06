package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PremiumSchedule {

	private Integer premiumscheduleid;
	private CustomerPolicyDetails cp1;
	private Date premiumdate;
	private PremiumPaidStatus ppstatus=PremiumPaidStatus.UNPAID;
	private HistoryOfPaidPremium historyofps ;
	
	public PremiumSchedule() {
		// TODO Auto-generated constructor stub
	}

	public PremiumSchedule(Integer premiumscheduleid, Policies policyid, User userid, Date premiumdate) {
		super();
		this.premiumscheduleid = premiumscheduleid;
		
		this.premiumdate = premiumdate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPremiumscheduleid() {
		return premiumscheduleid;
	}

	public void setPremiumscheduleid(Integer premiumscheduleid) {
		this.premiumscheduleid = premiumscheduleid;
	}

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = true)
	@JoinColumn(name="cpdetails")
	public CustomerPolicyDetails getCp1() {
		return cp1;
	}
	
	
	@Enumerated(EnumType.STRING)
	public PremiumPaidStatus getPpstatus() {
		return ppstatus;
	}

	public void setPpstatus(PremiumPaidStatus ppstatus) {
		this.ppstatus = ppstatus;
	}

	public void setCp1(CustomerPolicyDetails cp1) {
		this.cp1 = cp1;
	}

	@Temporal(TemporalType.DATE)
	public Date getPremiumdate() {
		return premiumdate;
	}

	public void setPremiumdate(Date premiumdate) {
		this.premiumdate = premiumdate;
	}

	
	@JsonIgnore
	@OneToOne
	public HistoryOfPaidPremium getHistoryofps() {
		return historyofps;
	}

	public void setHistoryofps(HistoryOfPaidPremium historyofps) {
		this.historyofps = historyofps;
	}

	@Override
	public String toString() {
		return "PremiumSchedule [premiumscheduleid=" + premiumscheduleid + ", cp1=" + cp1 + ", premiumdate="
				+ premiumdate + ", ppstatus=" + ppstatus + ", historyofps=" + historyofps + "]";
	}

	
	
	
}
