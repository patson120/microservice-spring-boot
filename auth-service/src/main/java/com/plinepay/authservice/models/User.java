package com.plinepay.authservice.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pline_pay_user")
public class User {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Basic(optional = false)
    // @Column(nullable = false)
    private Long id;
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String password;
    @Column( length = 255)
    private String roles;
}
