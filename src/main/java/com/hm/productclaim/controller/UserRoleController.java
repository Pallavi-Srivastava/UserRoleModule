package com.hm.productclaim.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hm.productclaim.dto.ResponseDTO;
import com.hm.productclaim.dto.UpdateDetailsDTO;
import com.hm.productclaim.exception.UserException;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.service.IUserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserRoleController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation("To get all user record")
	@GetMapping("/record")
	public ResponseEntity<ResponseDTO> getAllRecord() throws UserException {
		List<UserDetails> records = userService.viewAllRecord();
		System.out.println("Records"+records);
		return new ResponseEntity<>(new ResponseDTO(200,"All available user data",records), HttpStatus.OK);
	}
		
	@ApiOperation("To get all role list")
	@GetMapping("/role")
	public ResponseEntity<ResponseDTO> getAllRoleRecord() throws UserException {
		List<UserRole> role = userService.viewAllRole();
		System.out.println("Role"+role);
		return new ResponseEntity<>(new ResponseDTO(200,"All available user role",role), HttpStatus.OK);
	}
		
	@ApiOperation("Update record")
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateRecordExceptGivenNameAndId(@PathVariable("id")int id,@RequestBody UpdateDetailsDTO userDetailsDTO) throws UserException {
		UserDetails records = userService.updateUserDetails(id,userDetailsDTO);
		System.out.println("Updated Records"+records);
		return new ResponseEntity<>(new ResponseDTO(200,"Record Updated Successfully",records), HttpStatus.OK);
	}  
}
