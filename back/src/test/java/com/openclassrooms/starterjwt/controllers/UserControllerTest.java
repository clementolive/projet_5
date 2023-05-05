package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.mapper.UserMapper;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import com.openclassrooms.starterjwt.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Spy
    private UserMapper userMapper;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        User user = User.builder().id(5L).firstName("user").lastName("lastname").email("email")
                .password("password").admin(false).build();
        ResponseEntity<?> response = ResponseEntity.ok().body(userMapper.toDto(user));
        when(userService.findById(5L)).thenReturn(user);

        ResponseEntity<?> actual = userController.findById("5");

        assertEquals(response,actual);
        verify(userService, times(1)).findById(5L);
        verifyNoMoreInteractions(userService);
    }


    @Test
    @DisplayName("Delete a user (named save?)")
    //user.mail must be like userdetails.username
    void save() {
        User user = User.builder().id(5L).firstName("user").lastName("lastname").email("email")
                .password("password").admin(false).build();

        Authentication auth = mock(Authentication.class);
        UserDetails userDetails = new UserDetailsImpl(1L, "email", "firstname", "lastname",
                true, "password");
        when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        when(userService.findById(5L)).thenReturn(user);

        ResponseEntity<?> actual = userController.save("5");

        assertEquals(actual, ResponseEntity.ok().build());
        verify(userService, times(1)).findById(5L);
        verify(userService, times(1)).delete(5L);
        verifyNoMoreInteractions(userService);
    }
}