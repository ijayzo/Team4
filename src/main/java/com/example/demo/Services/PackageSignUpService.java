package com.example.demo.Services;

import com.example.demo.DAO.PackageSignUpRepository;
import com.example.demo.DTO.PackageSignUpRequest;
import com.example.demo.Exceptions.PackageDoesNotExist;
import com.example.demo.Models.EmployeePackages;
import com.example.demo.Models.PackageSignUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageSignUpService {

    @Autowired
    private PackageSignUpRepository packageSignUpRepository;

    @Autowired
    private RestTemplate  restTemplate;

    @Autowired
    private MailSender mailSender;

    private static Logger logger = LoggerFactory.getLogger(PackageSignUpService.class);



    public void create(PackageSignUpRequest packageSignUpRequest){
        logger.info("Creating a new PackageSignUp");
        PackageSignUp packageSignUp = new PackageSignUp();
        packageSignUp.setUsername(packageSignUpRequest.getUsername());
        packageSignUp.setFlightId(packageSignUpRequest.getFlightId());
        packageSignUp.setSignUpDate(new Date());
        packageSignUp.setPackageId(packageSignUpRequest.getPackageId());
        packageSignUp.setHotelId(packageSignUpRequest.getHotelId());
        packageSignUp.setDeleted(false);

        mailSender.createdPackage(packageSignUp);

        packageSignUpRepository.save(packageSignUp);
        logger.info("PackageSignUp Successfully");
    }


    public List<PackageSignUp> getAllPackageById(int packageId){
        logger.info("PackageSignUp Returning List for id " + packageId);
        return packageSignUpRepository.findPackageSignUpByPackageId(packageId).stream()
                .filter(packageSignUp -> packageSignUp.isDeleted() == false)
                .collect(Collectors.toList());

    }

    public List<PackageSignUp> getAllPackageByEmployee(String username){
        return packageSignUpRepository.findPackageSignUpByUsername(username).stream()
                .filter(packageSignUp -> packageSignUp.isDeleted() == false)
                .collect(Collectors.toList());
    }

    public void deletePackage(int packageSignUpId){
        Optional<PackageSignUp> getSignUp = packageSignUpRepository.findPackageSignUpByPackageSignUpId(packageSignUpId);
        if(getSignUp.isPresent()){
            PackageSignUp packageSignUp = getSignUp.get();
            packageSignUp.setDeleted(true);
            packageSignUpRepository.save(packageSignUp);
            logger.info("PackageSignUp Successfully Deleted");

        }else {
            throw new PackageDoesNotExist();
        }
    }


}
