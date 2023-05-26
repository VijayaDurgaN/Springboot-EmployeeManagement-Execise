package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmployeeDeptDTO {
    @Column(unique = true)
    private Integer aadhar;

    @NotBlank(message = "Name should not be blank")
    private String dept;
}
