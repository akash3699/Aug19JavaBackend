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
		
		
		String msg="Your are Successfully Registered in Online Policy Management System"+
		"Your details "+u.toString();
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
		return cpdid;
	}

	@Override
	public List<CustomerPolicyDetails> getUserPolicyDetails(int userid) {
//		User u = getUserDetails(userid);
//		String jpql = "select u.cp from User u where u.userid = :id";
		String jpql = "select cp from CustomerPolicyDetails cp where cp.userid.userId=:id";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).setParameter("id", userid).getResultList();
	}
}
