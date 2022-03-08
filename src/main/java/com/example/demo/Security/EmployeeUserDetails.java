package com.example.demo.Security;


import com.example.demo.Models.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EmployeeUserDetails implements UserDetails {

    private Employee employee;

    public EmployeeUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> getRoles = new HashSet<>();
        getRoles.add(new SimpleGrantedAuthority(employee.getRole().name()));
        return getRoles;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return  employee.isDeleted() == true ? false : true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return  employee.isDeleted() == true ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  employee.isDeleted() == true ? false : true;
    }

    @Override
    public boolean isEnabled() {
       return  employee.isDeleted() == true ? false : true;

    }
}
