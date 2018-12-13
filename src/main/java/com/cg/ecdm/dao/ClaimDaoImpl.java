package com.cg.ecdm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cg.ecdm.exceptions.ECMException;
import com.cg.ecdm.models.ClaimBean;

@Repository
public class ClaimDaoImpl implements IClaimDao{
	
	@Autowired
	MongoTemplate template;
	
	public static int autoGenerate(){
		Random rand=new Random();
		int n=100000+rand.nextInt(900000);
		return n;
	} 
	

	@Override
	public ClaimBean addClaimDetails(ClaimBean claim) {
		claim.setClaimCode(autoGenerate());
		try {
			Date sdate= new SimpleDateFormat("dd-MM-yyyy").parse(claim.getStartDate());
			Date edate= new SimpleDateFormat("dd-MM-yyyy").parse(claim.getEndDate());
			long sms=sdate.getTime();
			long ems=edate.getTime();
			if(sms-ems>=0){
				throw new ECMException("start date shouldn't be greater than end date");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ECMException e) {
			e.printStackTrace();
		}
		template.insert(claim);
		return claim;
	}


	@Override
	public ClaimBean getById(int claimCode) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(claimCode));
		if(template.findOne(query, ClaimBean.class)==null){
			try {
				throw new ECMException("No such Id exists "+claimCode);
			} catch (ECMException e) {
				e.printStackTrace();
			}
		}
		return template.findOne(query, ClaimBean.class);
	}


	@Override
	public void deleteClaim(ClaimBean bean) {
		Query query = new Query();
		System.out.println(bean.getClaimCode());
		query.addCriteria(Criteria.where("_id").is(bean.getClaimCode()));
		ClaimBean bean2 = template.findOne(query, ClaimBean.class);
		if(bean2==null){
			try {
				throw new ECMException("No such Id exists to delete"+bean.getClaimCode());
			} catch (ECMException e) {
				e.printStackTrace();
			}
		}
		template.remove(bean2);
	}


	@Override
	public ClaimBean updateClaimDetails(ClaimBean bean) {
		ClaimBean bean2 = getById(bean.getClaimCode());
		ClaimBean bean3 = new ClaimBean();
		if(bean2!=null)
		{
			bean3 = template.save(bean);
		}
		return bean3;
	}

}
