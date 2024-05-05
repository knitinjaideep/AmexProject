package com.amex.school.student.service;

import com.amex.school.common.GlobalConstants;
import com.amex.school.common.GlobalLogger;
import com.amex.school.common.StudentValidator;
import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import com.amex.school.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GlobalLogger logger;
    /**
     * Retrieves all students.
     *
     * @return List of students.
     */
    public List<Student> getAllStudents(){
        logger.logInfo(GlobalConstants.FETCH_ALL_STUDENTS);
        return studentRepository.findAll();
    }

    /**
     * Adds a new student.
     *
     * @param student The student to add.
     */
    public void addStudent(Student student) {
        logger.logInfo(GlobalConstants.ADD_NEW_STUDENT, student);
        StudentValidator.validateStudent(student);
        studentRepository.save(student);
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id The ID of the student.
     * @return Optional containing the student if found, otherwise empty.
     */
    public Optional<Student> getStudentById(Long id){
        logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_ID, id);
        Assert.notNull(id, "ID must not be null");
        return studentRepository.findById(id);
    }

    /**
     * Retrieves students by name.
     *
     * @param name The name of the students to retrieve.
     * @return List of students with the specified name.
     */
    public List<Student> getStudentsByName(String name){
        logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_NAME, name);
        Assert.hasText(name, "Name must not be empty");
        return studentRepository.findByName(name);
    }

    /**
     * Retrieves students by class name.
     *
     * @param className The class name of the students to retrieve.
     * @return List of students with the specified class name.
     */
    public List<Student> getStudentsByClassName(ClassNames className){
        logger.logInfo(GlobalConstants.FETCH_STUDENT_BY_CLASSNAME, className);
        Assert.notNull(className, "Class name must not be null");
        return studentRepository.findByClassName(className);
    }

    /**
     * Updates an existing student.
     *
     * @param id             The ID of the student to update.
     * @param studentDetails The updated student details.
     */
    public void updateStudent(Long id, Student studentDetails) {
        logger.logInfo(GlobalConstants.UPDATE_STUDENT, studentDetails);
        Assert.notNull(id, "ID must not be null");
        Assert.notNull(studentDetails, "Student details must not be null");
        StudentValidator.validateStudent(studentDetails);
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            // Update name and class_name fields
            student.setName(studentDetails.getName());
            student.setClassName(studentDetails.getClassName());
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
    }
}
