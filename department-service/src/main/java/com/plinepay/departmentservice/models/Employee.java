package com.plinepay.departmentservice.models;

public record Employee(
    Long id, Long departmentId, String name, int age, String position
) {
    
}
