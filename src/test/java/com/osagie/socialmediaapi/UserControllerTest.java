package com.osagie.socialmediaapi;

import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.enums.Role;
import com.osagie.socialmediaapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @WithMockUser("obi")
    public void testGetOneUser() throws Exception {

        User user = User.builder()
                .id(1)
                .email("obi@123.com")
                .role(Role.USER)
                .build();

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));


        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

    }

    @Test
    public void testGetOneUserUnAuth() throws Exception {

        User user = User.builder()
                .id(1)
                .email("obi@123.com")
                .role(Role.USER)
                .build();

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));


        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError()).andReturn();

        assertEquals(403, mvcResult.getResponse().getStatus());

    }

}
