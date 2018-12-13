package com.cg.ecdm.service;

import com.cg.ecdm.models.ClaimBean;

public interface IClaimService {

	ClaimBean addClaimDetails(ClaimBean claim);

	ClaimBean getById(int claimCode);

	void deleteClaim(ClaimBean bean);

	ClaimBean updateClaimDetails(ClaimBean bean);

}
