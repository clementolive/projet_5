package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.services.SessionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class SessionControllerTest {

    @InjectMocks
    SessionController sessionController;
    @Spy
    private SessionMapper sessionMapper;
    @Mock
    private SessionService sessionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void findById() {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        when(sessionService.getById(1L)).thenReturn(session);
        ResponseEntity<?>  response = ResponseEntity.ok().body(this.sessionMapper.toDto(session));

        ResponseEntity<?> actual = sessionController.findById("1");

        assertEquals(response,actual);
        verify(sessionService, times(1)).getById(1L);
        verifyNoMoreInteractions(sessionService);
    }
    @Test
    void findAll() {
        Session session = Session.builder().id(1L).name("Session 1").build();
        Session session2 = Session.builder().id(2L).name("Session 2").build();
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session);
        sessionList.add(session2);
        when(sessionService.findAll()).thenReturn(sessionList);
        ResponseEntity<?>  response = ResponseEntity.ok().body(this.sessionMapper.toDto(sessionList));

        ResponseEntity<?> actual = sessionController.findAll();

        assertEquals(response,actual);
        verify(sessionService, times(1)).findAll();
        verifyNoMoreInteractions(sessionService);
    }

    @Test
    @DisplayName("Delete a session (called save in controller ? )")
    //Given session exists
    void delete() {
        Session session = Session.builder()
                .id(1L).name("Session 1").description("session desc").date(new Date()).name("new session")
                .build();
        when(sessionService.getById(1L)).thenReturn(session);

        //Deleting!
        sessionController.save("1");

        verify(sessionService, times(1)).getById(1L);
        verify(sessionService, times(1)).delete(1L);
        verifyNoMoreInteractions(sessionService);
    }

    @Test
    // Already participating ? is verified in service.
    void participate() {
        ResponseEntity<?> response = ResponseEntity.ok().build();

        ResponseEntity<?> actual = sessionController.participate("1", "5");

        assertEquals(response,actual);
        verify(sessionService, times(1)).participate(1L, 5L);
        verifyNoMoreInteractions(sessionService);
    }

    @Test
    // Not Already participating ? is verified in service.
    void noLongerParticipate() {
        ResponseEntity<?> response = ResponseEntity.ok().build();

        ResponseEntity<?> actual = sessionController.noLongerParticipate("1", "5");

        assertEquals(response,actual);
        verify(sessionService, times(1)).noLongerParticipate(1L, 5L);
        verifyNoMoreInteractions(sessionService);
    }
}