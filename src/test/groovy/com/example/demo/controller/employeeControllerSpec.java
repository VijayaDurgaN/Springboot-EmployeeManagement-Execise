package com.example.demo.controller;

import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import spock.lang.Specification;

@SpringBootTest
@AutoConfigureMockMvc
public class employeeControllerSpec extends Specification {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;


}
