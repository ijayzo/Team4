package com.example.demo.Models;


import com.example.demo.DTO.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private boolean isDeleted;
}
