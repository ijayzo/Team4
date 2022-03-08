package com.example.demo.Controllers;

import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.DTO.DeleteRequest;
import com.example.demo.Services.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Employee Controller
 * Able to get all employees, create a new employee, delete an employee.
 */
@RestController
@CrossOrigin("${react.config.url}")
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);



    @GetMapping
    public ResponseEntity start(){
        logger.info("Probe Verify");
        return ResponseEntity.status(HttpStatus.OK).body("Probe Verified");
    }
    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        employeeServices.createEmployee(createEmployeeRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getAllEmployee());
    }


    @PostMapping("/deleteEmployee")
    public ResponseEntity deleteEmployee(@RequestBody DeleteRequest deleteRequest){
        employeeServices.deleteEmployee(deleteRequest.getEmployeeId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
