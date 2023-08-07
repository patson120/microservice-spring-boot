package com.plinepay.departmentservice.client;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.plinepay.departmentservice.models.Employee;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("/employees/departments/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId);
    
}
