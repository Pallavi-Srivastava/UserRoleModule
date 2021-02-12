package com.hm.productclaim.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.repository.UserRepository;
import com.hm.productclaim.repository.UserRoleRepository;

@RunWith(SpringRunner.class)
class UserServiceTest {
	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserRoleRepository userRoleRepository;

	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new UserDetails(1, "lovey", 2, 2), new UserDetails(958, "Huy", 4, 2)).collect(Collectors.toList()));
		assertEquals(3, service.viewAllRecord().size());
	}
	
	@Test
	public void getUserRoleTest() {
		when(userRoleRepository.findAll()).thenReturn(Stream
				.of(new UserRole(1, "IT_Admin"), new UserRole(2, "Empoyee")).collect(Collectors.toList()));
		assertEquals(2, service.viewAllRole().size());
	}
}
