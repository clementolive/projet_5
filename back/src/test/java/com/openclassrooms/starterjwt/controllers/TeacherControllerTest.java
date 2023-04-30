package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @InjectMocks
    TeacherController teacherController;
    @Spy
    private TeacherMapper teacherMapper;
    @Mock
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Teacher teacher = Teacher.builder().id(5L).firstName("teacher")
                .lastName("lastname").build();
        when(teacherService.findById(5L)).thenReturn(teacher);
        ResponseEntity<?>  response = ResponseEntity.ok().body(this.teacherMapper.toDto(teacher));

        ResponseEntity<?> actual = teacherController.findById("5");

        assertEquals(response,actual);
        verify(teacherService, times(1)).findById(5L);
        verifyNoMoreInteractions(teacherService);
    }

    @Test
    void findAll() {
        Teacher teacher = Teacher.builder().id(5L).firstName("teacher")
                .lastName("lastname").build();
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        when(teacherService.findAll()).thenReturn(teachers);
        ResponseEntity<?>  response = ResponseEntity.ok().body(this.teacherMapper.toDto(teachers));

        ResponseEntity<?> actual = teacherController.findAll();

        assertEquals(response,actual);
        verify(teacherService, times(1)).findAll();
        verifyNoMoreInteractions(teacherService);
    }
}