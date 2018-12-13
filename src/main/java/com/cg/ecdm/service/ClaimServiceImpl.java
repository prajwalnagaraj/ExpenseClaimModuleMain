package com.cg.ecdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecdm.dao.IClaimDao;
import com.cg.ecdm.models.ClaimBean;

@Service
public class ClaimServiceImpl implements IClaimService{
	
	@Autowired
	IClaimDao dao;

	@Override
	public ClaimBean addClaimDetails(ClaimBean claim) {
		return dao.addClaimDetails(claim);
	}

	@Override
	public ClaimBean getById(int claimCode) {
		return dao.getById(claimCode);
	}

	@Override
	public void deleteClaim(ClaimBean bean) {
dao.deleteClaim(bean);		
	}

	@Override
	public ClaimBean updateClaimDetails(ClaimBean bean) {
		return dao.updateClaimDetails(bean);
	}

}
