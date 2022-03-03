package com.example.demo.Controllers;

import com.example.demo.DTO.PackageSignUpRequest;
import com.example.demo.Services.PackageSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packageSignUp")
public class PackageSignUpController {

    @Autowired
    private PackageSignUpService packageSignUpService;


    @PostMapping("/create")
    public ResponseEntity create (@RequestBody PackageSignUpRequest packageSignUpRequest){
        packageSignUpService.create(packageSignUpRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity getSignUpByPackageId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(packageSignUpService.getAllPackageById(id));
    }

    @GetMapping("/getAllEmployee/{id}")
    public ResponseEntity getSignUpByEmployeeId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(packageSignUpService.getAllPackageByEmployee(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteSignUpById(@PathVariable int id){
        packageSignUpService.deletePackage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
