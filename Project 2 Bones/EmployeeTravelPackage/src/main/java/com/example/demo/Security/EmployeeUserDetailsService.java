package com.example.demo.Security;


import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Employee> employee = repository.getEmployeeByUsername(username);
       if(!employee.isPresent()){
           throw new UsernameNotFoundException("UserName Does Not Exist");
       }
        return new EmployeeUserDetails(employee.get());
    }
}
