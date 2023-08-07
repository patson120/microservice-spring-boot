package com.plinepay.departmentservice.controllers;

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

import com.plinepay.departmentservice.client.EmployeeClient;
import com.plinepay.departmentservice.models.Department;
import com.plinepay.departmentservice.reposirories.DepartmentRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired 
    DepartmentRepository departmentRepository;

    @Autowired 
    EmployeeClient employeeClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    
    @PostMapping()
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department added: {}", department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping()
    public List<Department> findAll(Department department) {
        LOGGER.info("Department find");
        return departmentRepository.findAll();
    }

    // Appel du service de gestion des employés
    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments =  departmentRepository.findAll();
        departments.forEach(department -> department.setEmployees(employeeClient.findByDepartmentId(department.getId())));
        return departments;
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        LOGGER.info("Department added: id = {}", id);
        return departmentRepository.findById(id);
    }
    
    
    
}
