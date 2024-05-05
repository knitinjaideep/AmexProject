package com.amex.school.student.service;

import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }
    public List<Student> getStudentsByName(String name){
        return studentRepository.findByName(name);
    }

    public List<Student> getStudentsByClassName(ClassNames className){
        return studentRepository.findByClassName(className);
    }

    public void updateStudent(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            // Update name and class_name fields
            student.setName(studentDetails.getName());
            student.setClassName(studentDetails.getClassName());
            studentRepository.save(student);
        }
    }
}
