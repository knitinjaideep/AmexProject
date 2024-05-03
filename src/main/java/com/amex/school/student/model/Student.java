package com.amex.school.student.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Student {

    private Long studentId;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate joiningDate;
    private ClassNames className;
}
