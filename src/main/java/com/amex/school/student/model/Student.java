package com.amex.school.student.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "joining_date")
    private LocalDate joiningDate;
    @Enumerated(EnumType.STRING)
    private ClassNames className;

    public Student(String name, LocalDate dateOfBirth, LocalDate joiningDate, ClassNames className) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
        this.className = className;
    }
}
