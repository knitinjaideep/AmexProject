package com.amex.school.student.controller;

import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/id/{student_id}")
    public Optional<Student> getStudentById(@PathVariable("student_id") Long studentId) {
        return studentService.getStudentById(studentId);
    }
    @GetMapping("/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/class/{class_name}")
    public List<Student> getStudentsByClassName(@PathVariable("class_name") String className) {
        ClassNames classNames = ClassNames.valueOf(className);
        return studentService.getStudentsByClassName(classNames);
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        // Parse date strings to LocalDate objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        student.setDateOfBirth(LocalDate.parse(student.getDateOfBirth().toString(), formatter));
        student.setJoiningDate(LocalDate.parse(student.getJoiningDate().toString(), formatter));
        studentService.addStudent(student);
    }

    @PutMapping("{student_id}")
    public void updateStudent(@PathVariable("student_id") Long studentId, @RequestBody Student studentDetails) {
        studentService.updateStudent(studentId, studentDetails);
    }
}
