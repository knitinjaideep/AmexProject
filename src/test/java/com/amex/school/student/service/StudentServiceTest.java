package com.amex.school.student.service;

import com.amex.school.common.GlobalConstants;
import com.amex.school.common.GlobalLogger;
import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GlobalLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository, logger);
    }

    @Test
    void testGetAllStudents() {
        // Mock repository response
        List<Student> students = Arrays.asList(
                new Student(1L, "John Doe", LocalDate.of(2000, 1, 1), LocalDate.of(2022, 9, 1), ClassNames.SCIENCE),
                new Student(2L, "Jane Smith", LocalDate.of(2001, 2, 2), LocalDate.of(2021, 8, 1), ClassNames.MATHEMATICS)
        );
        when(studentRepository.findAll()).thenReturn(students);

        // Test
        List<Student> result = studentService.getAllStudents();

        // Verify
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(ClassNames.SCIENCE, result.get(0).getClassName());
        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals(ClassNames.MATHEMATICS, result.get(1).getClassName());
        verify(logger, times(1)).logInfo(GlobalConstants.FETCH_ALL_STUDENTS);
    }

    @Test
    void testAddStudent() {
        // Mock repository save method
        when(studentRepository.save(any(Student.class))).thenReturn(new Student());

        // Test
        Student student = new Student("John Doe", LocalDate.of(2000, 1, 1), LocalDate.of(2022, 9, 1), ClassNames.SCIENCE);
        studentService.addStudent(student);

        // Verify
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(logger, times(1)).logInfo(eq(GlobalConstants.ADD_NEW_STUDENT), any(Student.class));
    }


    @Test
    void testGetStudentById_ExistingId() {
        // Mock repository response
        Student student = new Student(1L, "John Doe", LocalDate.of(2000, 1, 1), LocalDate.of(2022, 9, 1), ClassNames.SCIENCE);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Test
        Optional<Student> result = studentService.getStudentById(1L);

        // Verify
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals(ClassNames.SCIENCE, result.get().getClassName());
        verify(logger, times(1)).logInfo(GlobalConstants.FETCH_STUDENT_BY_ID, 1L);
    }

    @Test
    void testGetStudentById_NonExistingId() {
        // Mock repository response
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Test
        Optional<Student> result = studentService.getStudentById(1L);

        // Verify
        assertFalse(result.isPresent());
        verify(logger, times(1)).logInfo(GlobalConstants.FETCH_STUDENT_BY_ID, 1L);
    }

    //TODO: More tests for other methods to be added
}
