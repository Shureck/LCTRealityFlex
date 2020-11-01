package com.example.recserv.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    private Integer id;
    private String finance;
    private String schedule;
    private String training;
    private Integer age;
    private String sex;

}
