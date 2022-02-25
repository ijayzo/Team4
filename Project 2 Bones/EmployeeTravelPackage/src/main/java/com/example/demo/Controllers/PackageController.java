package com.example.demo.Controllers;

import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.Models.EmployeePackages;
import com.example.demo.Services.PackageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("package")
public class PackageController {

    @Autowired
    private PackageServices thePackageService;


    @GetMapping("/getAllPackages")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(thePackageService.getAll());
    }

    /** This will create and update a package
     *
     * @param newEmployeePackage
     * @return
     */
    @PostMapping("/createPackage")
    public ResponseEntity createEmployee(@RequestBody EmployeePackages newEmployeePackage){
        thePackageService.save(newEmployeePackage);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/getOnePackage/{packageID}")
    public ResponseEntity getOnePackage(@PathVariable int packageID){
        EmployeePackages thePackage = thePackageService.getPackageByID(packageID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(thePackage);
    }

    @DeleteMapping("/deletePackage/{packageID}")
    public ResponseEntity deletePackage(@PathVariable int packageID) {
        EmployeePackages thePackage = thePackageService.getPackageByID(packageID);
        if (thePackage==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        thePackage.setDeleted(true);
        thePackageService.save(thePackage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
