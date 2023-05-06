package com.openclassrooms.starterjwt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;
import java.util.Date;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SessionController sessionController;
    @Spy
    SessionMapper sessionMapper;
    @Resource
    private SessionRepository sessionRepository;

    @Resource
    private UserRepository userRepository;
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        sessionRepository.deleteAll();
    }

    @Test
    void create_and_update() throws Exception {
        /* Can't create a sessionDto or use a sessionMapper for testing, because of compatibility problems with LocalDateTime,
        even with the jackson-datatype-jsr310 dependency in pom.xml
         */
        JSONObject sessionDtoJSON = new JSONObject();
        sessionDtoJSON.put("name", "myname");
        sessionDtoJSON.put("date", "2023-12-11T11:40:49Z");
        sessionDtoJSON.put("teacher_id", 5);
        sessionDtoJSON.put("description", "my description");

        this.mockMvc.perform(post("/api/session").characterEncoding("utf-8")
                .contentType(APPLICATION_JSON).content(String.valueOf(sessionDtoJSON)))
                .andDo(print())
                .andExpect(status().isOk());

        //Then update that session
        /* Issue with the original Controller : Postman goes /api/session/id
        but SessionController for update() is /api/session{id} without slash.
        Added slash in update() route.
         */
        JSONObject sessionUpdatedJSON = new JSONObject();
        sessionUpdatedJSON.put("name", "my new name");
        sessionUpdatedJSON.put("date", "2023-12-11T11:40:49Z");
        sessionUpdatedJSON.put("teacher_id", 5);
        sessionUpdatedJSON.put("description", "my new description");

        this.mockMvc.perform(put("/api/session/1") //ID must be the session just created
                        .characterEncoding("utf-8")
                        .contentType(APPLICATION_JSON).content(String.valueOf(sessionUpdatedJSON)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_sessions() throws Exception {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        sessionRepository.save(session);

        this.mockMvc.perform(get("/api/session").characterEncoding("utf-8")
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("new session"));

        assert(this.sessionRepository.findAll().size() == 1);
    }

    @Test
    void getById() throws Exception {
        JSONObject sessionDtoJSON = new JSONObject();
        sessionDtoJSON.put("name", "myname");
        sessionDtoJSON.put("date", "2023-12-11T11:40:49Z");
        sessionDtoJSON.put("teacher_id", 5);
        sessionDtoJSON.put("description", "my description");

        this.mockMvc.perform(post("/api/session").characterEncoding("utf-8")
                        .contentType(APPLICATION_JSON).content(String.valueOf(sessionDtoJSON)))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/session/3").characterEncoding("utf-8")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void my_delete() throws Exception {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        sessionRepository.save(session);

        this.mockMvc.perform(delete("/api/session/1").characterEncoding("utf-8")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}