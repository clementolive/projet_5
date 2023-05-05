package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AuthController authControllerController;
    @Resource
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerUser_thenAuthenticate() throws Exception {
        JSONObject registerJSON = new JSONObject();
        registerJSON.put("lastName", "mylastname");
        registerJSON.put("firstName", "myfirstname");
        registerJSON.put("email", "email@test.com");
        registerJSON.put("password", "test!1234");

        //register
        this.mockMvc.perform(post("/api/auth/register").characterEncoding("utf-8")
                        .content(registerJSON.toString()).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assert(this.userRepository.findAll().size() == 1);

        //authenticate
        JSONObject loginJSON = new JSONObject();
        loginJSON.put("email", "email@test.com");
        loginJSON.put("password", "test!1234");
        this.mockMvc.perform(post("/api/auth/login").characterEncoding("utf-8")
                        .content(loginJSON.toString()).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}