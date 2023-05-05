package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void delete() {
        //No arrange needed

        userService.delete(10L);

        verify(userRepository, times(1)).deleteById(10L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User actual = userService.findById(1L);

        assertEquals(user, actual);
        verify(userRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }
}