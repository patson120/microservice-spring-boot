package com.plinepay.employeeservice.controllers;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plinepay.employeeservice.models.Employee;
import com.plinepay.employeeservice.repositories.EmployeeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee added: {} ", employee);
        return employeeRepository.add(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find:  id = {}", id);
        return employeeRepository.findById(id);
    }

    @GetMapping("/departments/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find:  id = {}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId);
    }
    
    
}
