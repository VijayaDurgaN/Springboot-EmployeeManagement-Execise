package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeDeptDTO;
import com.example.demo.exception.ApplicationException;
import com.example.demo.service.EmployeeService;
import com.example.demo.utility.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/employees/add")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeDTO employee) throws ApplicationException {
        Integer employeeInfo = employeeService.addEmployee(employee);
        String successMessage = "INSERT_SUCCESS with employeeId  " + employeeInfo;
        return ResponseHandler.generateResponse(successMessage, HttpStatus.CREATED);
    }

    @GetMapping("/employees/{aadhar}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable Integer aadhar) throws ApplicationException {
        EmployeeDTO employeeInfo = employeeService.getEmployee(aadhar);
        return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
    }

    @PutMapping("/employees/{aadhar}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Integer aadhar, @Valid @RequestBody EmployeeDeptDTO employee) throws
            ApplicationException {
        employeeService.updateEmployee(aadhar, employee.getDept());
        String successMessage = "UPDATE_SUCCESS";
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{aadhar}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer aadhar) throws ApplicationException {
        employeeService.deleteEmployee(aadhar);
        String successMessage = "DELETE_SUCCESS";
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }
}
