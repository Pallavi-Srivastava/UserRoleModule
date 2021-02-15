package com.hm.productclaim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.hm.productclaim.dto.UpdateDetailsDTO;
import com.hm.productclaim.exception.UserException;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.repository.UserRepository;
import com.hm.productclaim.repository.UserRoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private UserRoleRepository userRoleRepository;
	
	static List<UserDetails> userList;
    static List<UserRole> roleList;
 
    @BeforeAll
    public static void setUp() {
    	userList=new ArrayList<>();
    	userList.add(new UserDetails(1, "lia", 2, 2));
    	userList.add(new UserDetails(2, "Daniel", 3, 4));
    	roleList=new ArrayList<>();
    	roleList.add(new UserRole(1, "Employee"));
    	roleList.add(new UserRole(2, "Manager"));
    }
	
	@Test
	public void testGetUserDetails() throws UserException {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new UserDetails(1, "lovey", 2, 2), new UserDetails(2, "Huy", 4, 2)).collect(Collectors.toList()));
		assertEquals(2, service.viewAllRecord().size());
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetUserRole() {
		when(userRoleRepository.findAll()).thenReturn(Stream
				.of(new UserRole(1, "IT_Admin"), new UserRole(2, "Empoyee")).collect(Collectors.toList()));
		assertEquals(2, service.viewAllRole().size());
	}
	
	@Test
	public void testUpdateDetails() throws UserException{
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(2);
        userDetails.setUserName("Lia");
		userDetails.setDesignation(2);
        userDetails.setManager(2);
        
        UpdateDetailsDTO userDetailsDTO=new UpdateDetailsDTO(1,1);
        
		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(userDetails));
		userDetails.setDesignation(1);
		userDetails.setManager(1);
		Mockito.when(userRepository.save(userDetails)).thenReturn(userDetails);
		assertThat(service.updateUserDetails(2, userDetailsDTO)).isEqualTo(userDetails); 
	}
}
