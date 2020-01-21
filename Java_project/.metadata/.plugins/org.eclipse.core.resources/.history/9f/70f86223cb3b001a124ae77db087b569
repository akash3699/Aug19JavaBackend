package com.app.dao;

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
import com.app.pojos.HistoryOfPaidPremium;
import com.app.pojos.Policies;
import com.app.pojos.PremiumFrequency;
import com.app.pojos.PremiumPaidStatus;
import com.app.pojos.PremiumSchedule;

@Service
@Transactional
public class CustPolicyDaoImpl implements ICustPolicyDao {

	@Autowired
	SessionFactory sf;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public PremiumSchedule getNextPremiumSchedule(int custid) {
//		String jpql = "select ps from PremiumSchedule ps where ps.ppstatus='UNPAID' and ps.cp1.cpid = :id ORDER BY ps.premiumdate";
		String jpql = "select ps from PremiumSchedule ps where ps.ppstatus='UNPAID' and ps.premiumscheduleid = :id ORDER BY ps.premiumdate";
		return sf.getCurrentSession().createQuery(jpql, PremiumSchedule.class).setParameter("id", custid).setMaxResults(1)
				.getSingleResult();
	}
	
	@Override
	public PremiumSchedule getNextPremiumScheduleByCustPolicyId(int custid) {
		String jpql = "select ps from PremiumSchedule ps where ps.ppstatus='UNPAID' and ps.cp1.cpid = :id ORDER BY ps.premiumdate";
//		String jpql = "select ps from PremiumSchedule ps where ps.ppstatus='UNPAID' and ps.premiumscheduleid = :id ORDER BY ps.premiumdate";
		return sf.getCurrentSession().createQuery(jpql, PremiumSchedule.class).setParameter("id", custid).setMaxResults(1)
				.getSingleResult();
	}

	@Override
	public PremiumSchedule payNextPremiumSchedule(int custpolicyid) {
		PremiumSchedule pstobepaid = getNextPremiumSchedule(custpolicyid);
		CustomerPolicyDetails cpdetails = pstobepaid.getCp1();
		if(cpdetails.getPaidpremiumcount()<cpdetails.getTotalpremiumcount())
		{
			cpdetails.setPaidpremiumcount(cpdetails.getPaidpremiumcount()+1);
			
			
			pstobepaid.setPpstatus(PremiumPaidStatus.PAID);
			HistoryOfPaidPremium hpp = new HistoryOfPaidPremium();
			hpp.setPremiumschedule(pstobepaid);
			hpp.setPaiddate(new Date());
			
			int index = (int)sf.getCurrentSession().save(hpp);
			HistoryOfPaidPremium hpptobeupdated =  sf.getCurrentSession().get(HistoryOfPaidPremium.class, index);
			pstobepaid.setHistoryofps(hpptobeupdated);
			

			String msg="Paymenyt of Premium for Policy Name: "+cpdetails.getPolicyid().getPolicyname()+" has been received in Online Policy Management System."+
					"for amount of "+cpdetails.getPremiumamout()+" with transaction id "+index+" on "+ hpptobeupdated.getPaiddate().toLocaleString();
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(cpdetails.getUserid().getEmail());
					mailMessage.setSubject("Payment Acknowledgement of Policy Premium on Online Policy Management System");
					mailMessage.setText(msg);
					try
					{
						mailSender.send(mailMessage);
					}
					catch (MailException e) 
					{
						System.out.println("inside mail exception");
						e.printStackTrace();
						return null;
					}
			
			return pstobepaid;
		}
		
		return null;
	}
	
	@Override
	public CustomerPolicyDetails getCustPolicyDetailsByPremiumSchedule(int premiumscheduleid) {
		String jpql = "select ps.cp1 from PremiumSchedule ps where ps.ppstatus='UNPAID' and ps.premiumscheduleid = :id ORDER BY ps.premiumdate";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).setParameter("id", premiumscheduleid).setMaxResults(1)
				.getSingleResult();
		
	}
	
	@Override
	public CustomerPolicyDetails getCustPolicyDetails(int cpid) {
		String jpql = "select cp from CustomerPolicyDetails cp where cp.cpid=:id";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).setParameter("id", cpid).getSingleResult();
	}

	@Override
	public List<CustomerPolicyDetails> getUserPolicyDetailsWithPaidPremium(int userid) {
		String jpql = "select cp from CustomerPolicyDetails cp where cp.cpid=:id ";
		return sf.getCurrentSession().createQuery(jpql, CustomerPolicyDetails.class).setParameter("id", userid).getResultList();
	}

	@Override
	public HistoryOfPaidPremium getPaidDateByPremiumSchedule(int psid) {
		String jpql = "select hpp from HistoryOfPaidPremium hpp where hpp.premiumschedule.premiumscheduleid=:id";
		return sf.getCurrentSession().createQuery(jpql, HistoryOfPaidPremium.class).setParameter("id", psid).getSingleResult();
	}

}
