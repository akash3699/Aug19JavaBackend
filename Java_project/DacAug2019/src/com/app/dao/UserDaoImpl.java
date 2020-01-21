package com.app.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.ClaimTracker;
import com.app.pojos.CustomerPolicyDetails;
import com.app.pojos.Policies;
import com.app.pojos.PremiumFrequency;
import com.app.pojos.PremiumSchedule;
import com.app.pojos.User;





@Service
@Transactional
public class UserDaoImpl implements IUserDao {

	@Autowired
	SessionFactory sf;
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public Integer registerUser(User u) {
		 int id = 0;
		
			u.setPasswd(u.getPasswd());
			id =(Integer) sf.getCurrentSession().save(u);
		
		
		String msg="Dear "+u.getEmail()+", You are Successfully Registered in Online Policy Management System"+
		"Your Password is "+u.getPasswd();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(u.getEmail());
		mailMessage.setSubject("Registration on Online Policy Management System");
		mailMessage.setText(msg);
		try
		{
			mailSender.send(mailMessage);
		}
		catch (MailException e) 
		{
			System.out.println("inside mail exception");
			e.printStackTrace();
		}
		
		
		
		return id;
	}

//	public String getMD5(String data) throws NoSuchAlgorithmException
//    { 
//MessageDigest messageDigest=MessageDigest.getInstance("MD5");
//
//        messageDigest.update(data.getBytes());
//        byte[] digest=messageDigest.digest();
//        StringBuffer sb = new StringBuffer();
//        for (byte b : digest) {
//            sb.append(Integer.toHexString((int) (b & 0xff)));
//        }
//        return sb.toString();
//    }

	@Override
	public User loginUser(User user) {
		System.out.println(user);
		
		String jpql = "select u from User u where u.email = :em and u.passwd=:pass";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em", user.getEmail())
				.setParameter("pass", user.getPasswd()).getSingleResult();
	}

	@Override
	public User getUserDetails(int userid) {
		
		String jpql = "select u from User u where u.userId = :id";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", userid)
				.getSingleResult();
	}

	@Override
	public int updateUserDetails(User user) {
		sf.getCurrentSession().update(user);
		return 1;
	}

	@Override
	public int updateUserPasswd(int userId,User user) {
		User userToBeUpdated = getUserDetails(userId);
//		String crpt;
//		try {
//			 crpt = getMD5(user.getPasswd());
//			
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 0;
//		}
		userToBeUpdated.setPasswd(user.getPasswd());
//		sf.getCurrentSession().update(userToBeUpdated);
		System.out.println(userToBeUpdated);
		return 1;
	}

	@Override
	public int buyUserPolicy(CustomerPolicyDetails cpd,User u,Policies p) {
		
		
		cpd.setUserid(u);
		cpd.setPolicyid(p);
		int cpdid = (int)sf.getCurrentSession().save(cpd);
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
		for(int i=1;i<=cpd.getTotalpremiumcount();i++)
		{
			PremiumSchedule p1 = new PremiumSchedule();
//			p1.setPremiumdate();
			if(cpd.getPs()==PremiumFrequency.WEEKLY)
			{
				
				
		        c.add(Calendar.DATE, 7);
			}
			else if(cpd.getPs()==PremiumFrequency.MONTHLY)
			{
				
				
		        c.add(Calendar.DATE, 30);
			}
			else if(cpd.getPs()==PremiumFrequency.YEARLY)
			{
				
				
		        c.add(Calendar.DATE, 365);
			}
			
			Date premiumDate = c.getTime();
			p1.setPremiumdate(premiumDate);
			p1.setCp1(cpd);
			cpd.addPremiumSchedule(p1);
		}
		sf.getCurrentSession().update(cpd);
		
		
		String msg="Dear "+u.getEmail()+", You are successfully purchased "+ cpd.getPolicyid().getPolicyname() 
				+" with Total Insurance Amount of "+cpd.getPolicyid().getTotalinsuranceamount()+" on "+cpd.getBuydate().toLocaleString();
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(u.getEmail());
				mailMessage.setSubject("Policy Purchased on Online Policy Management System");
				mailMessage.setText(msg);
				try
				{
					mailSender.send(mailMessage);
				}
				catch (MailException e) 
				{
					System.out.println("inside mail exception");
					e.printStackTrace();
					return 0;
				}
		
		
		
		
		
		
		
		return cpdid;
	}

	@Override
	public List<CustomerPolicyDetails> getUserPolicyDetails(int userid) {
//		User u = getUserDetails(userid);
//		String jpql = "select u.cp from User u where u.userid = :id";
		String jpql = "select cp from CustomerPolicyDetails cp where cp.userid.userId=:id";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).setParameter("id", userid).getResultList();
	}
	
	static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	
	

	@Override
	public User getUserByEmail(String email) {
		String jpql = "select u from User u where u.email = :em";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em", email)
				.getSingleResult();
	}

	@Override
	public int forgotUserPassword(String email) {
		User user = getUserByEmail(email);
		user.setPasswd(getAlphaNumericString(10));
		
		String msg="Your are Password has been changed in Online Policy Management System."+
				"Your new Password is "+user.getPasswd()+" After Login Please Change the Password";
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("Password Changed on Online Policy Management System");
				mailMessage.setText(msg);
				try
				{
					mailSender.send(mailMessage);
				}
				catch (MailException e) 
				{
					System.out.println("inside mail exception");
					e.printStackTrace();
					return 0;
				}
		
		
		
		return 1;
	}

	@Override
	public List<CustomerPolicyDetails> getAllUserPolicyDetails() {
		String jpql = "select cp from CustomerPolicyDetails cp";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).getResultList();
	}

	@Override
	public List<ClaimTracker> getClaimTrackerDetails(int userid) {
//		String jpql = "select u.claimtracker from User u where u.userId=:id";
		String jpql = "select ct from ClaimTracker ct where ct.user.userId=:id";
		return sf.getCurrentSession().createQuery(jpql, ClaimTracker.class).setParameter("id", userid).getResultList();
	}

	@Override
	public Integer AddClaimTrackerDetails(ClaimTracker ct, int userid) {
		User u = getUserDetails(userid);
		ct.setUser(u);
		int ctindex = (int) sf.getCurrentSession().save(ct);
		ClaimTracker cttobeupdated = sf.getCurrentSession().get(ClaimTracker.class, ctindex);
		u.addClaimTracker(cttobeupdated);
		return ctindex;
	}

	@Override
	public ClaimTracker getClaimTrackerDetailsByCTid(int claimtrackerid) {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().get(ClaimTracker.class, claimtrackerid);
	}

	@Override
	public Integer UpdateClaimTrackerDetailsByCTid(ClaimTracker ct, int claimtrackerid) {
		ClaimTracker cttobeupadted = getClaimTrackerDetailsByCTid(claimtrackerid);
		cttobeupadted.setClaimstatus(ct.getClaimstatus());
		cttobeupadted.setRemarks(ct.getRemarks());
		return 1;
	}

	@Override
	public List<ClaimTracker> getAllClaimTrackerDetails() {
		// TODO Auto-generated method stub
		String jpql = "select ct from ClaimTracker ct";
		return sf.getCurrentSession().createQuery(jpql, ClaimTracker.class).getResultList();
	} 
	
	
	
	
	
}
