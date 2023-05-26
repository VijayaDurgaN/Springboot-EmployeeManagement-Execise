package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @Id
    @Column(unique = true)
    private Integer aadhar;

    @NotBlank(message = "Name should not be blank")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should be alphabets only")
    private String name;

    @NotBlank(message = "dept should not be blank")
    private String dept;

    @NotBlank(message = "city should not be blank")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", message = "date of birth should be in format dd/mm/yyyy")
    private String dateOfBirth;

    private Integer age;

}


