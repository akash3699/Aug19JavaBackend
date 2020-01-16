package com.app.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		try {
			u.setPasswd(getMD5(u.getPasswd()));
			id =(Integer) sf.getCurrentSession().save(u);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
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

	public String getMD5(String data) throws NoSuchAlgorithmException
    { 
MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }

	@Override
	public User loginUser(User user) {
		System.out.println(user);
		try {
			user.setPasswd(getMD5(user.getPasswd()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String crpt;
		try {
			 crpt = getMD5(user.getPasswd());
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		userToBeUpdated.setPasswd(crpt);
//		sf.getCurrentSession().update(userToBeUpdated);
		System.out.println(userToBeUpdated);
		return 1;
	}
}
