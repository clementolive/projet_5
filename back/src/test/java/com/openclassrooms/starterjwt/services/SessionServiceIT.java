package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class SessionServiceIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private SessionService sessionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create_then_find_it() {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        this.sessionService.create(session);

        //With both find methods
        Session actual = this.sessionService.getById(1L);
        assertEquals(session, actual);

        List<Session> findAllActual = this.sessionService.findAll();
        assertEquals(findAllActual.size(), 1);

        //Finally, delete it
        this.sessionService.delete(1L);
    }

}