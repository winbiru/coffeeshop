package com.mycompany.coffeeshop.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.coffeeshop.Model.LoginRequest;
import com.mycompany.coffeeshop.Model.SignupRequest;
import com.mycompany.coffeeshop.Model.User;
import com.mycompany.coffeeshop.Service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void registerUser_validInput_returnsUser() throws Exception {
        SignupRequest signupRequest = new SignupRequest("testuser", "password", Collections.singletonList("USER").toString());
        User user = new User(1L, "testuser", "password", Collections.singletonList("USER"));

        when(userService.registerUser(any(SignupRequest.class))).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
                        .content(objectMapper.writeValueAsString(signupRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        User responseUser = objectMapper.readValue(responseContent, User.class);

        assert responseUser.getId().equals(1L);
        assert responseUser.getUsername().equals("testuser");
        assert responseUser.getRoles().equals(Collections.singletonList("USER"));
    }

    @Test
    public void login_validCredentials_returnsAuthToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        String token = "test.token";

        when(userService.login(any(LoginRequest.class))).thenReturn(token);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "Bearer " + token))
                .andReturn();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getUserInfo_authenticatedUser_returnsUserInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/user"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("User information"))
                .andReturn();
    }
}
