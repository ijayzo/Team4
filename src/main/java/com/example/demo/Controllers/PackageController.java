package com.example.demo.Controllers;

import com.example.demo.DTO.createPackageRequest;
import com.example.demo.DTO.getPackageByPackageIdRequest;
import com.example.demo.Models.EmployeePackages;
import com.example.demo.Services.PackageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Packages Controller
 * Able to get all Packages, create packages, and update packages.
 * Also, having a specific package ID, one can delete or get that specific package.
 */
@RestController
@RequestMapping("package")
@CrossOrigin("${react.config.url}")
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
    public ResponseEntity createPackage(@RequestBody createPackageRequest newEmployeePackage){
        thePackageService.save(newEmployeePackage);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/updatePackage")
    public ResponseEntity updatePackage(@RequestBody EmployeePackages newEmployeePackage){
        thePackageService.update(newEmployeePackage);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/getAll")
        public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(thePackageService.getAll());

        }


    @PostMapping("/getOnePackage")
    public ResponseEntity getOnePackage(@RequestBody getPackageByPackageIdRequest getPackageByPackageIdRequest){
        EmployeePackages thePackage = thePackageService.getPackageByID(getPackageByPackageIdRequest.getPackageId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(thePackage);
    }

    @PostMapping("/deletePackage")
    public ResponseEntity deletePackage(@RequestBody getPackageByPackageIdRequest getPackageByPackageIdRequest) {
        EmployeePackages thePackage = thePackageService.getPackageByID(getPackageByPackageIdRequest.getPackageId());
        if (thePackage==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        thePackage.setDeleted(true);
        thePackageService.delete(thePackage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
