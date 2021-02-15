package com.hm.productclaim.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.productclaim.dto.ResponseDTO;
import com.hm.productclaim.model.UserDetails;
import com.hm.productclaim.model.UserRole;
import com.hm.productclaim.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class UserRoleControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
    private UserService service;

    @InjectMocks
    private UserRoleController controller;

    static List<UserDetails> userList;
    static List<UserRole> roleList;
    static UserDetails detailsDTO;
    
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
    public void givenUserRole_WhenDisplayTheList_ShouldReturnUserDetailsList() throws Exception {
    	UserDetails user=new UserDetails(1, "lia", 2, 2);
    	when(service.viewAllRecord()).thenReturn(userList);
    	RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/user/record");
       MvcResult mvcResult = mvc.perform(requestBuilder)
          .andExpect(status().isOk()).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(status,200);
       assertEquals(objectMapper.writeValueAsString(new ResponseDTO(200,"OK",userList)),mvcResult.getResponse().getContentAsString());
    }
    
    @Test
    public void givenUserRole_WhenDisplayTheList_ShouldReturnUserRoleList() throws Exception {
    	UserRole role = new UserRole(1, "Employee");
    	when(service.viewAllRole()).thenReturn(roleList);
    	RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/user/role");
       MvcResult mvcResult = mvc.perform(requestBuilder)
          .andExpect(status().isOk()).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(status,200);
       assertEquals(objectMapper.writeValueAsString(new ResponseDTO(200,"OK",roleList)),mvcResult.getResponse().getContentAsString());
    }
    
    @Test
    public void shouldBeUpdateRecord() throws Exception {
    	UserDetails user = new UserDetails(2, "Daniel", 5, 6);
    	when(service.updateUserDetails(Mockito.anyInt(),Mockito.any())).thenReturn(user);
    	String updateString=objectMapper.writeValueAsString(user);
    	RequestBuilder requestBuilder=MockMvcRequestBuilders.put("/user/2")
    		.accept(MediaType.APPLICATION_JSON)
    		.content(updateString)
    		.contentType(MediaType.APPLICATION_JSON);
    	
       MvcResult mvcResult = mvc.perform(requestBuilder)
    	  .andDo(print())
          .andExpect(status().isOk()).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(status,200);
       assertEquals(objectMapper.writeValueAsString(new ResponseDTO(200,"OK",user)),mvcResult.getResponse().getContentAsString());
    }  
}
