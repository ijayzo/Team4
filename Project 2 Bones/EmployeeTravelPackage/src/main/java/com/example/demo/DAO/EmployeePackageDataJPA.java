package com.example.demo.DAO;


import com.example.demo.Models.EmployeePackages;
import org.springframework.data.jpa.repository.JpaRepository;

/** DataJPA Implementation for Employee_Packages Table
 *
 */
public interface EmployeePackageDataJPA extends JpaRepository <EmployeePackages, Integer> {


}
