package com.example.demo.Services;


import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.Exceptions.UserAlreadyExist;
import com.example.demo.Models.Employee;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createEmployee(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = new Employee();
        int generateEmployeeId = Integer.parseInt(RandomStringUtils.randomNumeric(8));
        if(isEmployeeExist(createEmployeeRequest.getUsername())){
            throw new UserAlreadyExist();
        }
        employee.setEmployeeId(generateEmployeeId);
        employee.setEmail(createEmployeeRequest.getEmail());
        employee.setFirstName(createEmployeeRequest.getFirstName());
        employee.setLastName(createEmployeeRequest.getLastName());
        employee.setUsername(createEmployeeRequest.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(createEmployeeRequest.getPassword());
        employee.setPassword(encodedPassword);
        employee.setRole(createEmployeeRequest.getRole());


        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }


    private boolean isEmployeeExist(String username){
        Optional<Employee> findEmployeeByUserName = employeeRepository.getEmployeeByUsername(username);
        return findEmployeeByUserName.isPresent() ? true : false;
    }
}
