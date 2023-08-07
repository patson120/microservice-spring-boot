package com.plinepay.employeeservice.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plinepay.employeeservice.models.Employee;

@Repository
public class EmployeeRepository {


    public List<Employee> employees = new ArrayList<Employee>();

    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee findById(Long id) {
        return employees.stream().filter(empl -> empl.id().equals(id))
            .findFirst()
            .orElseThrow();
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees.stream().filter(empl -> empl.departmentId().equals(departmentId))
            .toList();
    }

    public List<Employee> findAll() {
        return employees;
    }
    
}
