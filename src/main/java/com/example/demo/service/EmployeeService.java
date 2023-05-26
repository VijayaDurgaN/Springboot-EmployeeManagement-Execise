package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ApplicationException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class EmployeeService {

    private final String employeeNotDFoundMessage = "Employee not found in directory";
    private final String employeeExists = "Employee already exists";
    private final String futureDate = "Date of birth cannot be a future/current date.";
    @Autowired
    EmployeeRepository employeeRepository;

    public LocalDate parseDateOfBirth(String dateOfBirth) throws ApplicationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateOfBirth, formatter);
        LocalDate curDate = LocalDate.now();
        if (date.isEqual(curDate) | date.isAfter(curDate)) throw new ApplicationException(futureDate);
        return date;
    }

    public Integer calculateAge(String dateOfBirth) {
        LocalDate curDate = LocalDate.now();
        Integer age = Period.between(LocalDate.parse(dateOfBirth), curDate).getYears();
        return age;
    }

    public Integer addEmployee(EmployeeDTO employeeDTO) throws ApplicationException {
        Optional<Employee> employeeAadhar = employeeRepository.findById(employeeDTO.getAadhar());
        if (employeeAadhar.isEmpty()) throw new ApplicationException(employeeExists);
        LocalDate parsedDateOfBirth = parseDateOfBirth(employeeDTO.getDateOfBirth());
        Integer age = calculateAge(String.valueOf(parsedDateOfBirth));
        Employee employee = Employee.builder()
                .aadhar(employeeDTO.getAadhar())
                .name(employeeDTO.getName())
                .dept(employeeDTO.getDept())
                .city(employeeDTO.getCity())
                .dateOfBirth(parsedDateOfBirth)
                .age(age).build();
        return employee.getAadhar();
    }

    public EmployeeDTO getEmployee(Integer aadhar) throws ApplicationException {
        Optional<Employee> employeeInfo = employeeRepository.findById(aadhar);
        Employee employee = employeeInfo.orElseThrow(() -> new ApplicationException(employeeNotDFoundMessage));
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .aadhar(employee.getAadhar())
                .name(employee.getName())
                .dept(employee.getDept())
                .city(employee.getCity())
                .dateOfBirth(String.valueOf(employee.getDateOfBirth()))
                .age(employee.getAge()).build();
        return employeeDTO;
    }

    public void updateEmployee(Integer aadhar, String dept) throws ApplicationException {
        Optional<Employee> employeeById = employeeRepository.findById(aadhar);
        Employee employee = employeeById.orElseThrow(() -> new ApplicationException(employeeNotDFoundMessage));
        employee.setDept(dept);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer aadhar) throws ApplicationException {
        Optional<Employee> employeeById = employeeRepository.findById(aadhar);
        Employee employee = employeeById.orElseThrow(() -> new ApplicationException(employeeNotDFoundMessage));
        employeeRepository.deleteById(aadhar);
    }


}
