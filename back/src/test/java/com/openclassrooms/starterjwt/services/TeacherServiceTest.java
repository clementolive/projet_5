package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @InjectMocks
    TeacherService teacherService;

    @Mock
    TeacherRepository teacherRepository;

    Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);
        when(teacherRepository.findAll()).thenReturn(teacherList);

        List<Teacher> actual = teacherService.findAll();

        assertEquals(actual, teacherList);
        verify(teacherRepository, times(1)).findAll();
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    void findById() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher actual = teacherService.findById(1L);

        assertEquals(actual, teacher);
        verify(teacherRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(teacherRepository);
    }
}