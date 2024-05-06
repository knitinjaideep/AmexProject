package com.amex.school.student.controller;

import com.amex.school.common.GlobalLogger;
import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {
    @Mock
    private StudentService studentService;

    @Mock
    private GlobalLogger logger;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<?> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void testGetStudentById() {
        long studentId = 1L;
        Optional<Student> student = Optional.of(new Student());
        when(studentService.getStudentById(studentId)).thenReturn(student);

        ResponseEntity<?> response = studentController.getStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }
    @Test
    void testGetStudentsByName() {
        String name = "John";
        List<Student> students = new ArrayList<>();
        when(studentService.getStudentsByName(name)).thenReturn(students);

        ResponseEntity<?> response = studentController.getStudentsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void testGetStudentsByClassName() {
        String className = "MATHEMATICS";
        List<Student> students = new ArrayList<>();
        when(studentService.getStudentsByClassName(ClassNames.valueOf(className))).thenReturn(students);

        ResponseEntity<?> response = studentController.getStudentsByClassName(className);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void testAddNewStudent_success() {
        Student student = new Student();
        student.setName("Nitin Kotcherlakota");
        student.setDateOfBirth(LocalDate.parse("1997-09-14"));
        student.setJoiningDate(LocalDate.parse("2024-03-01"));
        student.setClassName(ClassNames.MATHEMATICS);
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        doNothing().when(studentService).addStudent(student);
        ResponseEntity<String> response = studentController.addNewStudent(student);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void testUpdateStudent() {
        long studentId = 1L;
        Student student = new Student();
        student.setName("Nitin Kotcherlakota");
        student.setDateOfBirth(LocalDate.parse("1997-09-14"));
        student.setJoiningDate(LocalDate.parse("2024-03-01"));
        student.setClassName(ClassNames.SCIENCE);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Student updated successfully");
        doNothing().when(studentService).updateStudent(studentId, student);

        ResponseEntity<String> response = studentController.updateStudent(studentId, student);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void testAddNewStudentFailure_MissingName() {
        // Create a student with missing name
        Student student = new Student();
        student.setDateOfBirth(LocalDate.parse("1997-09-14"));
        student.setJoiningDate(LocalDate.parse("2024-03-01"));
        student.setClassName(ClassNames.MATHEMATICS);

        // Configure service to throw exception
        doThrow(new IllegalArgumentException("Name must not be null")).when(studentService).addStudent(student);

        // Call controller method
        ResponseEntity<String> response = studentController.addNewStudent(student);

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Failed to add a new student. Name must not be null", response.getBody());
    }

    @Test
    void testUpdateStudentFailure_StudentNotFound() {
        // Create a student with valid data
        long studentId = 1L;
        Student student = new Student();
        student.setName("Nitin Kotcherlakota");
        student.setDateOfBirth(LocalDate.parse("1997-09-14"));
        student.setJoiningDate(LocalDate.parse("2024-03-01"));
        student.setClassName(ClassNames.SCIENCE);

        // Configure service to return empty optional, indicating student not found
        doThrow(new IllegalArgumentException("Student not found")).when(studentService).updateStudent(studentId, student);

        // Call controller method
        ResponseEntity<String> response = studentController.updateStudent(studentId, student);

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Failed to update the student. Student not found", response.getBody());
    }

    @Test
    void testUpdateStudentFailure_InvalidDateFormat() {
        // Create a student with invalid date format
        long studentId = 1L;
        Student student = new Student();
        student.setName("Nitin Kotcherlakota");
        student.setDateOfBirth(LocalDate.parse("1997-09-14"));
        student.setJoiningDate(LocalDate.parse("2024-03-01"));
        student.setClassName(ClassNames.SCIENCE);

        // Configure service to throw exception for invalid date format
        doThrow(new IllegalArgumentException("Invalid date format")).when(studentService).updateStudent(studentId, student);

        // Call controller method
        ResponseEntity<String> response = studentController.updateStudent(studentId, student);

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Failed to update the student. Invalid date format", response.getBody());
    }

    @Test
    void testDeleteStudentFailure_StudentNotFound() {
        // Configure service to throw exception indicating student not found
        doThrow(new IllegalArgumentException("Student not found")).when(studentService).deleteStudent(1L);

        // Call controller method
        ResponseEntity<String> response = studentController.deleteStudent(1L);

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Failed to delete the student. Student not found", response.getBody());
    }
}