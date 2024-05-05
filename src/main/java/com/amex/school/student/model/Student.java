package com.amex.school.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Represents a Student entity.
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth is required")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "joining_date")
    @NotNull(message = "Joining date is required")
    @JsonProperty("joining_date")
    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_name")
    @NotNull(message = "Class name is required")
    @JsonProperty("class_name")
    private ClassNames className;

    public Student(String name, LocalDate dateOfBirth, LocalDate joiningDate, ClassNames className) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
        this.className = className;
    }
}
