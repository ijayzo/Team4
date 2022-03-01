package com.example.demo.Controllers;

import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.Services.EmployeeServices;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    /**
     *
     * @param createEmployeeRequest the request needed to create an Employee
     * @return a status OK
     */
    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        employeeServices.createEmployee(createEmployeeRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity start(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     *
     * @return the list of all Employees
     */
    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getAllEmployee());
    }

}
