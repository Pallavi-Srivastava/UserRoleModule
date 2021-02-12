package com.hm.productclaim;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.junit.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.productclaim.controller.UserRoleController;
import com.hm.productclaim.dto.ResponseDTO;
import com.hm.productclaim.exception.UserException;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.repository.UserRepository;
import com.hm.productclaim.repository.UserRoleRepository;
import com.hm.productclaim.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductClaimApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserRoleRepository userRoleRepository;
	
	@Test
	public void getUserRecordTest() {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new UserDetails(1, "lovely", 2, 2), new UserDetails(958, "Huy", 4, 2)).collect(Collectors.toList()));
		assertEquals(2, service.viewAllRecord().size());
	}
	
	@Test
	public void getUserRoleTest() {
		when(userRoleRepository.findAll()).thenReturn(Stream
				.of(new UserRole(1, "IT_Admin"), new UserRole(2, "Empoyee")).collect(Collectors.toList()));
		assertEquals(2, service.viewAllRole().size());
	}
	
	@Test
	   public void updateDetailsTest() throws Exception {
	     
	   }
}
