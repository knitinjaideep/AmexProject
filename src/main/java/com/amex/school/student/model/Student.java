package com.amex.school.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @JsonProperty("name")
    private String name;
    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "joining_date")
    @JsonProperty("joining_date")
    private LocalDate joiningDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "class_name")
    @JsonProperty("class_name")
    private ClassNames className;

    public Student(String name, LocalDate dateOfBirth, LocalDate joiningDate, ClassNames className) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
        this.className = className;
    }
}
