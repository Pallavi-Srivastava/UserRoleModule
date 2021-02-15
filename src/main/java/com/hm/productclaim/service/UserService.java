package com.hm.productclaim.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hm.productclaim.dto.UpdateDetailsDTO;
import com.hm.productclaim.exception.UserException;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.repository.UserRepository;
import com.hm.productclaim.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserDetails> viewAllRecord() throws UserException  {
		List<UserDetails> userDetails= userRepository.findAll(); 
		if (userDetails.isEmpty()) {
            throw new UserException("Records are not available", UserException.ExceptionType.RECORD_NOT_AVAILABLE);
        }
		log.debug("Getting record data from DB : " + userDetails);
         return userDetails;
	}
	
	@Override
	public List<UserRole> viewAllRole() {
		List<UserRole> userRole= userRoleRepository.findAll(); 
		log.debug("Getting record data from DB : " + userRole);
         return userRole;
	}
	
	@Override
	public UserDetails getUserDetailsById(int id) throws UserException {
		return userRepository.findById(id).orElseThrow(
				() -> new UserException("UserDetails with user id " + id + " does not exist ", UserException.ExceptionType.RECORD_NOT_AVAILABLE));
	}
	
	@Override
	public UserDetails updateUserDetails(int id, UpdateDetailsDTO userDetailsDTO) throws UserException {
		UserDetails userDetails = this.getUserDetailsById(id);
		userDetails.setDesignation(userDetailsDTO.designation);
		userDetails.setManager(userDetailsDTO.manager);
		log.debug("Getting updatedRecord data from DB : " + userDetails);
		return userRepository.save(userDetails);
	}
}
