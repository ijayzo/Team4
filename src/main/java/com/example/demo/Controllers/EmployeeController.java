package com.example.demo.Controllers;

import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.Services.EmployeeServices;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3030") // TODO: Dont hard Code this
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
        logger.info("I am Ready");
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
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
