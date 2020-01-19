package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dao.ICustPolicyDao;
import com.app.pojos.CustomerPolicyDetails;
import com.app.pojos.HistoryOfPaidPremium;
import com.app.pojos.PremiumPaidStatus;
import com.app.pojos.PremiumSchedule;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/custpolicy")
public class CustPolicyController {
	
	@Autowired
	ICustPolicyDao icustpolicydao;
	
	
	public CustPolicyController() {
		System.out.println("inside CustPolicyController");
	}
	
	
	@GetMapping("/getnextpremiumschedule/{custpolicyid}")
	public PremiumSchedule getNextPremiumSchedule(@PathVariable int custpolicyid)
	{
		return icustpolicydao.getNextPremiumSchedule(custpolicyid);
	}
	
	@GetMapping("/getnextpremiumschedulebycustpolicyid/{custpolicyid}")
	public PremiumSchedule getNextPremiumScheduleByCustPolicyId(@PathVariable int custpolicyid)
	{
		return icustpolicydao.getNextPremiumScheduleByCustPolicyId(custpolicyid);
	}
	
	@GetMapping("/getcustpolicydetailsbypremiumschedule/{premiumscheduleid}")
	public CustomerPolicyDetails getCustPolicyDetailsByPremiumSchedule(@PathVariable int premiumscheduleid)
	{
		return icustpolicydao.getCustPolicyDetailsByPremiumSchedule(premiumscheduleid);
	}
	
	@GetMapping("/getcustpolicydetailswithpaidpremium/{userid}")
	public List<CustomerPolicyDetails> getCustPolicyDetailsWithPaidPremium(@PathVariable int userid)
	{
//		List<CustomerPolicyDetails> cpdetailsupdated=new ArrayList<CustomerPolicyDetails>(); 
//		List<CustomerPolicyDetails> cpdetails = icustpolicydao.getUserPolicyDetailsWithPaidPremium(userid);
//		for (CustomerPolicyDetails customerPolicyDetails : cpdetails) {
//			cpdetailsupdated.add(customerPolicyDetails);
//		}
//		
//		
//		for (CustomerPolicyDetails customerPolicyDetailsUPDATED : cpdetailsupdated) {
//			customerPolicyDetailsUPDATED.setPremiumschedule(new ArrayList<>());
//		
//		
//		
//		for (CustomerPolicyDetails customerPolicyDetails : cpdetails) {
//			
//			
//			for (PremiumSchedule premiumschedule : customerPolicyDetails.getPremiumschedule()) {
//				if(premiumschedule.getPpstatus()==PremiumPaidStatus.PAID)
//				{
//
//					customerPolicyDetailsUPDATED.getPremiumschedule().add(premiumschedule);
//				}
//			}
//		}
//		
//		}
//		return cpdetailsupdated;
		return icustpolicydao.getUserPolicyDetailsWithPaidPremium(userid);
	}
	
	
	@GetMapping("/paynextpremiumschedule/{custpolicyid}")
	public PremiumSchedule PayNextPremiumSchedule(@PathVariable int custpolicyid)
	{
		return icustpolicydao.payNextPremiumSchedule(custpolicyid);
	}
	
	@GetMapping("/gethistoryofpaidpremiumbypremiumscheduleid/{psid}")
	public HistoryOfPaidPremium getHistoryOfPaidPremiumByPremiumScheduleId(@PathVariable int psid)
	{
		return icustpolicydao.getPaidDateByPremiumSchedule(psid);
	}

}
