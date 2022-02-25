package com.example.demo.Controllers;

import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.Services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;


    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        employeeServices.createEmployee(createEmployeeRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getAllEmployee());
    }

}
