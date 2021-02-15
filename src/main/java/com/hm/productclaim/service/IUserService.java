package com.hm.productclaim.service;

import java.util.List;
import com.hm.productclaim.dto.UpdateDetailsDTO;
import com.hm.productclaim.exception.UserException;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;

public interface IUserService {
	
	List<UserDetails> viewAllRecord() throws UserException;
	
	List<UserRole> viewAllRole();
		
	UserDetails getUserDetailsById(int id) throws UserException;
	
	UserDetails updateUserDetails(int id, UpdateDetailsDTO userDetailsDTO) throws UserException;
}
