package com.app.dao;

import java.util.List;

import com.app.pojos.Policies;

public interface IPolicyDao {
	public Integer registerPolicy(Policies p);
	public Policies getPolicyById(int pid);
	public List<Policies> getAllPolicies();
	public Integer updatePolicy(Policies p);
}
