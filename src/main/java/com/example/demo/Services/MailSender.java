package com.example.demo.Services;

import com.example.demo.DAO.EmployeePackageDataJPA;
import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.DTO.MailSenderCreatePackage;
import com.example.demo.Models.Employee;
import com.example.demo.Models.EmployeePackages;
import com.example.demo.Models.PackageSignUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MailSender {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePackageDataJPA employeePackageDataJPA;

    @Value("${email.config.url}")
    private String emailUrl;

    private static Logger logger = LoggerFactory.getLogger(MailSender.class);



    public String createdPackage(PackageSignUp packageSignUp){
        Optional<Employee> employees = employeeRepository.getEmployeeByUsername(packageSignUp.getUsername());
        Optional<EmployeePackages> packages = employeePackageDataJPA.findById(packageSignUp.getPackageId());

        if(employees.isPresent()){
            logger.info("Employee Exist");
            Employee employee = employees.get();
            MailSenderCreatePackage mailSenderCreate = new MailSenderCreatePackage();
            mailSenderCreate.setUsername(employee.getUsername());
            mailSenderCreate.setEmail(employee.getEmail());

            restTemplate.postForEntity(emailUrl+"/mailapi/createdPackage", mailSenderCreate, null);
                logger.info("Information sent to EmailAPI");
        }else {
            logger.error("User Does Not Exist ");
        }

        return null;




    }


    public String createdEmployee(Employee employee){
        logger.debug("Sending Employee");
            MailSenderCreatePackage mailSenderCreate = new MailSenderCreatePackage();
            mailSenderCreate.setUsername(employee.getUsername());
            mailSenderCreate.setEmail(employee.getEmail());

            restTemplate.postForEntity(emailUrl+"/mailapi/createdEmployee", mailSenderCreate, null);
            logger.info("Information sent to EmailAPI");

            return "";


    }
}
