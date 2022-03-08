package com.example.demo.Services;


import com.example.demo.Controllers.EmployeeController;
import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.DTO.EmployeeResponse;
import com.example.demo.Exceptions.UserAlreadyExist;
import com.example.demo.Exceptions.UserDoesNotExist;
import com.example.demo.Models.Employee;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServices {
    private static Logger logger = LoggerFactory.getLogger(EmployeeServices.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MailSender mailSender;

    public void createEmployee(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = new Employee();
        int generateEmployeeId = Integer.parseInt(RandomStringUtils.randomNumeric(8));
        if(isEmployeeExist(createEmployeeRequest.getUsername())){
            logger.debug("User Already Exist in the Database");
            throw new UserAlreadyExist();
        }

        employee.setEmployeeId(generateEmployeeId);
        employee.setEmail(createEmployeeRequest.getEmail());
        employee.setFirstName(createEmployeeRequest.getFirstName());
        employee.setLastName(createEmployeeRequest.getLastName());
        employee.setUsername(createEmployeeRequest.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(createEmployeeRequest.getPassword());
        employee.setPassword(encodedPassword);
        employee.setRole(createEmployeeRequest.getRole());
        logger.info("Service for creating a new Employee. Setting roles is included here as well");
        mailSender.createdEmployee(employee);

        employeeRepository.save(employee);
        logger.info("Employee Created Successfully");
    }

    /**
     *
     * @return a list of all Employee in the company
     */
    public List<EmployeeResponse> getAllEmployee(){
        logger.info("Service for getting all employees.");
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.isDeleted() == false)
                .map(employee -> {
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setEmployeeId(employee.getEmployeeId());
                    employeeResponse.setEmail(employee.getEmail());
                    employeeResponse.setFirstName(employee.getFirstName());
                    employeeResponse.setLastName(employee.getLastName());
                    employeeResponse.setRole(employee.getRole());
                    return employeeResponse;

                }).collect(Collectors.toList());

    }

    public void deleteEmployee(int employeeId){
        logger.info("Service for deleting an employee.");
        Optional<Employee> getEmployee = employeeRepository.getEmployeeByEmployeeId(employeeId);
        if(getEmployee.isPresent()){
            logger.info("User is Present");
            Employee employee = getEmployee.get();
            System.out.println(employee.getFirstName());
            employee.setDeleted(true);
            employeeRepository.save(employee);
        }else{
            logger.info("Exception created in case user does not exist.");
            throw new UserDoesNotExist();
        }
    }


    private boolean isEmployeeExist(String username){
        logger.info("Service for checking if Employee exists.");
        Optional<Employee> findEmployeeByUserName = employeeRepository.getEmployeeByUsername(username);
        if(findEmployeeByUserName.isPresent()){
            Employee employee = findEmployeeByUserName.get();
            if(employee.isDeleted()){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
