package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dao.IPolicyDao;
import com.app.pojos.Policies;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/policy")
public class PolicyController {
	@Autowired
	IPolicyDao dao;
	
	@PostMapping("/register")
	public Integer register(@RequestBody Policies p)
	{
		System.out.println(p);
		
		return dao.registerPolicy(p);
	}
	
	@GetMapping("/{pid}")
	public Policies getPolicyDetails(@PathVariable int pid)
	{
		System.out.println(pid);
		
		return dao.getPolicyById(pid);
	}
	
	@GetMapping("/allpolicies")
	public List<Policies> getAllPolicyDetails()
	{
		System.out.println("in get AllPolicyDetails");
		
		return dao.getAllPolicies();
	}

}
