package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    public Integer age;
    @Id
    private Integer aadhar;
    private String name;
    private String dept;
    private String city;
    private LocalDate dateOfBirth;

}