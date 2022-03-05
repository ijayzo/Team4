package com.example.demo.Security;

import com.example.demo.DTO.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private EmployeeJWTRequestFilter employeeJWTRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .antMatchers("/employee/createEmployee").permitAll()
                .antMatchers("/employee/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/package/**").permitAll() // TODO: Change this later one all mappings are completed
                .antMatchers("/flights/**").permitAll() // TODO: Change this later one all mappings are completed
               .antMatchers("/employee/getAllEmployees").permitAll()//TODO: Restrict It From Employees

                .anyRequest().authenticated();

        http.addFilterBefore(employeeJWTRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
