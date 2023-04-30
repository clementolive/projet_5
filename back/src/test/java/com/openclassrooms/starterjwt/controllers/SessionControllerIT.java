package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = {  SessionController.class, SessionService.class})
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Inject
    MockMvc mockMvc;

    @Autowired
    SessionController sessionController;

    @Resource
    private SessionRepository sessionRepository;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @Disabled
    void myTest() throws Exception {
        SessionDto sessionDto = new SessionDto();
        this.mockMvc.perform(post("/api/session").content(String.valueOf(sessionDto))).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void get_sessions() throws Exception {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        sessionRepository.save(session);

        this.mockMvc.perform(get("/api/session").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("new session"));

        //assertThat(this.sessionRepository.findAll()).has(has)
        assert(this.sessionRepository.findAll().size() == 1);
    }
}