package com.example.demo.Services;

import com.example.demo.DAO.EmployeePackageDataJPA;
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

    public void save(EmployeePackages thePackage) {
        empPackageDao.save(thePackage);
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

}
