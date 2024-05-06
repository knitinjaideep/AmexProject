package com.amex.school.student.controller;

import com.amex.school.common.GlobalConstants;
import com.amex.school.common.GlobalLogger;
import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling student-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final GlobalLogger logger;

    /**
     * Helper method to handle exceptions uniformly and return ResponseEntity with appropriate status code and error message.
     * @param message Error message
     * @param ex Exception
     * @return ResponseEntity with error message and status code
     */
    private ResponseEntity<String> handleException(String message, Exception ex) {
        logger.logError(message, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + message + ". " + ex.getMessage());
    }

    /**
     * Retrieves all students.
     *
     * @return ResponseEntity containing a list of students or an error message
     */
    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        try {
            logger.logInfo(GlobalConstants.FETCH_ALL_STUDENTS);
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_FETCH_ALL_STUDENTS, ex);
            return handleException(GlobalConstants.ERROR_FETCH_ALL_STUDENTS, ex);
        }
    }

    /**
     * Retrieves a student by ID.
     *
     * @param studentId The ID of the student to retrieve
     * @return ResponseEntity containing the student or an error message
     */
    @GetMapping("/id/{student_id}")
    public ResponseEntity<?> getStudentById(@PathVariable("student_id") Long studentId) {
        try {
            logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_ID);
            Optional<Student> student =  studentService.getStudentById(studentId);
            if (student.isPresent()) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with ID: " + studentId);
            }
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_FETCH_STUDENT_BY_ID, ex);
            return handleException(GlobalConstants.ERROR_FETCH_STUDENT_BY_ID, ex);
        }
    }

    /**
     * Retrieves students by name.
     *
     * @param name The name of the students to retrieve
     * @return ResponseEntity containing a list of students or an error message
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentsByName(@PathVariable String name) {
        try{
            logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_NAME, name);
            List<Student> students = studentService.getStudentsByName(name);
            return ResponseEntity.ok(students);
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_FETCH_STUDENT_BY_NAME, ex);
            return handleException(GlobalConstants.ERROR_FETCH_STUDENT_BY_NAME, ex);
        }
    }

    /**
     * Retrieves students by class name.
     *
     * @param className The class name of the students to retrieve
     * @return ResponseEntity containing a list of students or an error message
     */
    @GetMapping("/class/{class_name}")
    public ResponseEntity<?> getStudentsByClassName(@PathVariable("class_name") String className) {
        try{
            logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_CLASSNAME, className);
            ClassNames classNames = ClassNames.valueOf(className);
            List<Student> students =  studentService.getStudentsByClassName(classNames);
            return ResponseEntity.ok(students);
        } catch (Exception ex){
            logger.logError(GlobalConstants.ERROR_FETCH_STUDENT_BY_CLASSNAME, ex);
            return handleException(GlobalConstants.ERROR_FETCH_STUDENT_BY_CLASSNAME, ex);
        }

    }

    /**
     * Adds a new student.
     *
     * @param studentDetails The details of the student to add
     * @return ResponseEntity indicating success or failure
     */
    @PostMapping
    public ResponseEntity<String> addNewStudent(@RequestBody Student studentDetails){
        try {
            logger.logInfo(GlobalConstants.ADD_NEW_STUDENT, studentDetails);
            // Parse date strings to LocalDate objects
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            studentDetails.setDateOfBirth(LocalDate.parse(studentDetails.getDateOfBirth().toString(), formatter));
            studentDetails.setJoiningDate(LocalDate.parse(studentDetails.getJoiningDate().toString(), formatter));
            studentService.addStudent(studentDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_ADD_NEW_STUDENT, ex);
            return handleException(GlobalConstants.ERROR_ADD_NEW_STUDENT, ex);
        }
    }

    /**
     * Updates an existing student.
     *
     * @param studentId      The ID of the student to update
     * @param studentDetails The updated details of the student
     * @return ResponseEntity indicating success or failure
     */
    @PutMapping("{student_id}")
    public ResponseEntity<String> updateStudent(@PathVariable("student_id") Long studentId, @RequestBody Student studentDetails) {
        try {
            logger.logInfo(GlobalConstants.UPDATE_STUDENT, studentDetails);
            studentService.updateStudent(studentId, studentDetails);
            return ResponseEntity.ok("Student updated successfully");
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_UPDATE_STUDENT, ex);
            return handleException(GlobalConstants.ERROR_UPDATE_STUDENT, ex);
        }
    }

    /**
     * Deletes an existing student.
     *
     * @param studentId The ID of the student to delete
     * @return ResponseEntity indicating success or failure
     */
    @DeleteMapping(path="{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId){
        try {
            logger.logInfo(GlobalConstants.DELETE_STUDENT, studentId);
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok("Student delete successfully");
        } catch (Exception ex) {
            logger.logError(GlobalConstants.ERROR_DELETE_STUDENT);
            return handleException(GlobalConstants.ERROR_DELETE_STUDENT, ex);
        }
    }

}
