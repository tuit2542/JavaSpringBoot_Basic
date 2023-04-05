package com.example.demo.student.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_class")
@Getter
@Setter
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "class_id")
    private Long id;

    @Column(name = "class_name")
    private String name;

}
