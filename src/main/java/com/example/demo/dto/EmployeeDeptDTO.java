package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class EmployeeDeptDTO {
    @Column(unique = true)
    private Integer aadhar;

    @NotBlank(message = "Name should not be blank")
    private String dept;

    public EmployeeDeptDTO(Integer aadhar, String dept) {
        this.aadhar = aadhar;
        this.dept = dept;
    }

    public Integer getAadhar() {
        return aadhar;
    }

    public void setAadhar(Integer aadhar) {
        this.aadhar = aadhar;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
