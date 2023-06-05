package com.example.demo.service

import com.example.demo.dto.EmployeeDTO
import com.example.demo.entity.Employee
import com.example.demo.exception.ApplicationException
import com.example.demo.repository.EmployeeRepository
import com.example.demo.service.EmployeeService
import spock.lang.Specification

import java.time.LocalDate

class EmployeeServiceSpec extends Specification {
    def employee = new Employee(1,2,"abc","ece","chennai",LocalDate.now())
    def employeeDTO = new EmployeeDTO(1,"ABC","ECE","chennai","12/03/21",1)
    def employeeRepoMock = Mock(EmployeeRepository)
    def employeeService = new EmployeeService(employeeRepoMock)

    def 'should create employee when aadhaar id does not exist'() {
        given:
        def employeeDTO = EmployeeDTO.builder().aadhar(1)
                .name("abc")
                .dept("ece")
                .city("chennai")
                .dateOfBirth("12/03/2021")
                .age(12).build()
        when:
        employeeService.addEmployee(employeeDTO)
        then:
        1 * employeeRepoMock.findByAadhar(1)
    }

    def "should throw ApplicationException when employee already exists"() {
        given:
        employeeRepoMock.findByAadhar(_) >> Optional.of(employee)

        when:
        employeeService.addEmployee(employeeDTO)

        then:
        thrown(ApplicationException)
    }

    def 'should return employee when given aadhaar is valid'() {
        given:
        def employeeDTO = EmployeeDTO.builder().aadhar(1)
                .name("abc")
                .dept("ece")
                .city("chennai")
                .dateOfBirth("12/03/2021")
                .age(12).build()
        when:
        employeeService.getEmployee(1167443)
        then:
        1 * employeeRepoMock.findByAadhar(1167443) >> Optional.of(employee)
    }

    def 'should throw exception when given aadhaar is invalid'() {
        given:
        def employeeDTO = EmployeeDTO.builder().aadhar(1)
                .name("abc")
                .dept("ece")
                .city("chennai")
                .dateOfBirth("12/03/2021")
                .age(12).build()
        when:
        employeeService.getEmployee(1)
        then:
        thrown(ApplicationException)
    }

    def "should update department of employee when valid aadhar is given"() {
        given:
        def employee = new Employee(1,2,"abc","ece","chennai",LocalDate.now())

        when:
        employeeService.updateEmployee(1000,"marketing")

        then:
        1 * employeeRepoMock.findByAadhar(1000) >> Optional.of(employee)
        employee.dept == "marketing"
    }

    def "should throw exception when update of employee department done with invalid aadhar"() {
        given:
        employeeRepoMock.findByAadhar(100) >> Optional.empty()

        when:
        employeeService.updateEmployee(100,"marketing")

        then:
        thrown(ApplicationException)
    }


}
