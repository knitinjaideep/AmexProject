package com.amex.school.common;

import com.amex.school.student.model.ClassNames;
import com.amex.school.student.model.Student;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * The StudentValidator class is responsible for validating student data such as name, date of birth,
 * joining date, and class name.
 */

public class StudentValidator {

    /**
     * Validates the provided student object.
     * @param student The student object to be validated.
     * @throws IllegalArgumentException if the student object is invalid.
     */
    public static void validateStudent(Student student) {
        Assert.notNull(student, "Student must not be null");
        validateDateOfBirth(student.getDateOfBirth());
        validateJoiningDate(student.getJoiningDate(), student.getDateOfBirth());
        validateName(student.getName());
        validateClassName(student.getClassName());
    }

    /**
     * Validates the date of birth.
     * @param dateOfBirth The date of birth to be validated.
     * @throws IllegalArgumentException if the date of birth is invalid.
     */
    private static void validateDateOfBirth(LocalDate dateOfBirth) {
        Assert.notNull(dateOfBirth, "Date of birth must not be null");
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
    }

    /**
     * Validates the joining date.
     * @param joiningDate The joining date to be validated.
     * @param dateOfBirth The date of birth of the student.
     * @throws IllegalArgumentException if the joining date is invalid.
     */
    private static void validateJoiningDate(LocalDate joiningDate, LocalDate dateOfBirth) {
        Assert.notNull(joiningDate, "Joining date must not be null");
        if (joiningDate.isAfter(dateOfBirth)) {
            throw new IllegalArgumentException("Joining date cannot be after date of birth");
        }
    }

    /**
     * Validates the student's name.
     * @param name The name to be validated.
     * @throws IllegalArgumentException if the name is invalid.
     */
    private static void validateName(String name) {
        Assert.hasText(name, "Name must not be empty");
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters");
        }
    }

    /**
     * Validates the class name.
     * @param className The class name to be validated.
     * @throws IllegalArgumentException if the class name is invalid.
     */
    private static void validateClassName(ClassNames className) {
        Assert.notNull(className, "Class name must not be null");
    }
}
