package com.example.demo.Services;

import com.example.demo.DAO.EmployeePackageDataJPA;
import com.example.demo.DTO.createPackageRequest;
import com.example.demo.Models.EmployeePackages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for Employee_Package methods using the EmployeePackageDataJPA Implementation
 */
@Service
public class PackageServices {

    private EmployeePackageDataJPA empPackageDao;

    @Autowired
    public PackageServices(EmployeePackageDataJPA theEmpPackageDao) {
        empPackageDao = theEmpPackageDao;
    }

    public void save(createPackageRequest thePackage){
        EmployeePackages employeePackages = new EmployeePackages();
        employeePackages.setPackageCost(thePackage.getPackageCost());
        employeePackages.setPackageDays(thePackage.getPackageDays());
        employeePackages.setPackageDescription(thePackage.getPackageDescription());
        employeePackages.setPackageCategory(thePackage.getPackageCategory());
        employeePackages.setPackageStatus(thePackage.getPackageStatus());
        employeePackages.setTravelDate(thePackage.getTravelDate());
        employeePackages.setTotalPackageSignUp(thePackage.getTotalPackageSignUp());
        employeePackages.setTravelDestination(thePackage.getTravelDestination());
        employeePackages.setDeleted(false);



        empPackageDao.save(employeePackages);
    }

    public void update(EmployeePackages packageRequest){
        empPackageDao.save(packageRequest);

    }

    public List<EmployeePackages> getAll() {
        return empPackageDao.findAll();
    }

    public EmployeePackages getPackageByID(int id) {
        return empPackageDao.findById(id).get();
    }

    public void deletePackageByID(int id) {
        empPackageDao.deleteById(id);
    }

    public void delete(EmployeePackages packages){
        empPackageDao.save(packages);
    }

}
