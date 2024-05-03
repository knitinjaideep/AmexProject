package com.amex.school.student.controller;

import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents(){
        return Arrays.asList(
                new Student(1L, "Nitin", LocalDate.of(1992,9,14), LocalDate.now(), ClassNames.COMPUTER_SCIENCE),
                new Student(2L, "Nitin", LocalDate.of(1992,9,14), LocalDate.now(), ClassNames.COMPUTER_SCIENCE)
        );
    }

}
