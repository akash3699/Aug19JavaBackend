package com.app.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IPolicyDao;
import com.app.dao.IUserDao;
import com.app.pojos.CustomerPolicyDetails;
import com.app.pojos.Policies;
import com.app.pojos.User;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserDao iuserDao;
	@Autowired
	IPolicyDao ipolicyDao;
	
	
	
	@PostMapping("/register")
	public Integer register(@RequestBody User user)
	{
		System.out.println(user);
		return iuserDao.registerUser(user);
	}
	
	@PostMapping("/buypolicy")
	public Integer register(@RequestBody CustomerPolicyDetailsWithUserAndPolicy cpdetails)
	{
		System.out.println(cpdetails);
		User u = iuserDao.getUserDetails(cpdetails.getUserid());
		Policies p = ipolicyDao.getPolicyById(cpdetails.getPolicyid());
		CustomerPolicyDetails cpd = new CustomerPolicyDetails();
		cpd.setBuydate(Calendar.getInstance().getTime());
		cpd.setPremiumamout(cpdetails.getPremiumamout());
		cpd.setPs(cpdetails.getPs());
		cpd.setTotalpremiumcount(cpdetails.getTotalpremiumcount());
		
		return iuserDao.buyUserPolicy(cpd, u, p);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user)
	{
		System.out.println(user);
		
		return iuserDao.loginUser(user);
	}
	
	@GetMapping("/userdetails/{userid}")
	public User getUser(@PathVariable int userid)
	{
		System.out.println(userid);
		
		return iuserDao.getUserDetails(userid);
	}
	
	@PutMapping("/userdetails/{userid}")
	public int updateUser(@PathVariable int userid,@RequestBody User user)
	{
		System.out.println(userid);
		System.out.println(user);
		
		return iuserDao.updateUserDetails(user);
	}
	
	@PutMapping("/userdetails/changepasswd/{userId}")
	public int updateUserPasswd(@PathVariable int userId,@RequestBody User user)
	{
		System.out.println(userId);
		System.out.println(user);
		
		return iuserDao.updateUserPasswd(userId,user);
	}
	
	@GetMapping("/userpolicydetails/{userid}")
	public List<CustomerPolicyDetails> getUserPolicies(@PathVariable int userid)
	{
		System.out.println(userid);
		
		return iuserDao.getUserPolicyDetails(userid);
	}
	
}
