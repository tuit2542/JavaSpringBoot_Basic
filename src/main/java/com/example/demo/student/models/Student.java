package com.example.demo.student.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "tb_student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "student_id")
    private Long id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_email")
    private String email;
    @Column(name = "student_birth_date")
    private LocalDate dob;

    @Column(name = "student_age")
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = this.getAge();
    }

    public Student(String name, String email, LocalDate dob ) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", dob=" + dob + ", age=" + age + '}';
    }
}
