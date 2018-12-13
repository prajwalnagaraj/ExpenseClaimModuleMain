package com.cg.ecdm.dao;

import com.cg.ecdm.models.ClaimBean;

public interface IClaimDao {

	ClaimBean addClaimDetails(ClaimBean claim);

	ClaimBean getById(int claimCode);

	void deleteClaim(ClaimBean bean);

	ClaimBean updateClaimDetails(ClaimBean bean);

}
