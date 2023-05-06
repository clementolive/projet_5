package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository; //Used in participate()

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @Disabled
    void findAll() {
        Session sessionToFind1 = Session.builder().id(1L).name("Session 1").build();
        Session sessionToFind2 = Session.builder().id(2L).name("Session 2").build();
        List<Session>  listToTest = new ArrayList<>();
        listToTest.add(sessionToFind1);
        listToTest.add(sessionToFind2);
        when(sessionRepository.findAll()).thenReturn(listToTest);

        List<Session> sessionList = sessionService.findAll();

        assertEquals(sessionList, listToTest);
        verify(sessionRepository, times(1)).findAll();
        verifyNoMoreInteractions(sessionRepository);
    }

    @Test
    void getById() {
        Session sessionToFind = Session.builder().id(10L).name("New session").build();
        when(sessionRepository.findById(10L)).thenReturn(Optional.ofNullable(sessionToFind));

        Session actual = sessionService.getById(10L);

        assertEquals(sessionToFind, actual);
        verify(sessionRepository, times(1)).findById(10L);
        verifyNoMoreInteractions(sessionRepository);

    }

    @Test
    @Disabled
    void update() {
        Session sessionToUpdate = Session.builder().id(1L).name("New session").build();
        when(sessionRepository.save(sessionToUpdate)).thenReturn(sessionToUpdate);

        sessionService.update(10L, sessionToUpdate);

        assertEquals(sessionToUpdate.getId(), 10L);
        verify(sessionRepository, times(1)).save(sessionToUpdate);
        verifyNoMoreInteractions(sessionRepository);
    }

    @Test
    //Given user not already participating
    void participate() {
        List <User> usersList = new ArrayList<>();
        Session sessionAddingUser = Session.builder().users(usersList).name("New session").build();
        User user = User.builder().id(5L).firstName("user").lastName("lastname").email("email")
                .password("password").admin(false).build();
        when(userRepository.findById(5L)).thenReturn(Optional.ofNullable(user));
        when(sessionRepository.findById(1L)).thenReturn(Optional.ofNullable(sessionAddingUser));
        assert sessionAddingUser != null;
        when(sessionRepository.save(sessionAddingUser)).thenReturn(sessionAddingUser);

        sessionService.participate(1L, 5L);

        verify(userRepository, times(1)).findById(anyLong());
        verify(sessionRepository, times(1)).findById(anyLong());
        verify(sessionRepository, times(1)).save(any(Session.class));
        verifyNoMoreInteractions(sessionRepository);
    }

    @Test
    //Given user participating
    void noLongerParticipate() {
        User user = User.builder().id(5L).firstName("user").lastName("lastname").email("email")
                .password("password").admin(false).build();
        List <User> usersList = new ArrayList<>();
        usersList.add(user);
        Session sessionDeletingUser = Session.builder().users(usersList).name("New session").build();

        when(sessionRepository.findById(1L)).thenReturn(Optional.ofNullable(sessionDeletingUser));

        sessionService.noLongerParticipate(1L, 5L);

        verify(sessionRepository, times(1)).findById(anyLong());
        verify(sessionRepository, times(1)).save(any(Session.class));
    }
}