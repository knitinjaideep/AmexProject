package com.amex.school.student.repository;

import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClassName(ClassNames className);

    List<Student> findByName(String name);
}
