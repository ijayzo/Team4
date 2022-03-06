package com.example.demo.Controllers;

import com.example.demo.DTO.PackageSignUpPackageIdRequest;
import com.example.demo.DTO.PackageSignUpRequest;
import com.example.demo.DTO.PackageSignUpUserNameRequest;
import com.example.demo.Services.PackageSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packageSignUp")
@CrossOrigin("${react.config.url}")
public class PackageSignUpController {

    @Autowired
    private PackageSignUpService packageSignUpService;


    @PostMapping("/create")
    public ResponseEntity create (@RequestBody PackageSignUpRequest packageSignUpRequest){
        packageSignUpService.create(packageSignUpRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/getPackageByPackageId")
    public ResponseEntity getSignUpByPackageId(@RequestBody PackageSignUpPackageIdRequest packageSignUpPackageIdRequest){
        return ResponseEntity.status(HttpStatus.OK).body(packageSignUpService.getAllPackageById(packageSignUpPackageIdRequest.getPackageId()));
    }

    @PostMapping("/getPackageByEmployeeId")
    public ResponseEntity getSignUpByEmployeeId(@RequestBody PackageSignUpUserNameRequest packageSignUpEmployeeIdRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .body(packageSignUpService.getAllPackageByEmployee(packageSignUpEmployeeIdRequest.getUsername()));
    }

    @PostMapping("/deletePackageSignUp")
    public ResponseEntity deleteSignUpById(@RequestBody PackageSignUpPackageIdRequest packageSignUpPackageIdRequest){
        packageSignUpService.deletePackage(packageSignUpPackageIdRequest.getPackageId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
