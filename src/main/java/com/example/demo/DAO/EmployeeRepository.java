package com.example.demo.DAO;



import com.example.demo.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 */

/**
 * Repository for Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> getEmployeeByUsername(String username);
    Optional<Employee> getEmployeeByEmployeeId(int employeeId);
}
