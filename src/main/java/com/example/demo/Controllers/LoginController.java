package com.example.demo.Controllers;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.Security.EmployeeJWT;
import com.example.demo.Security.EmployeeUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("${REACT_URL}") // TODO: Dont hard Code this
public class LoginController {

    @Autowired
    private EmployeeJWT employeeJWT;

    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;




    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println("Group test");
        final UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = employeeJWT.generateToken(userDetails);
      String getUsername = userDetails.getAuthorities().stream().findFirst().get().toString();
       String username = getUsername == null ? "" : getUsername;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponse( userDetails.getUsername(), username, token));
    }



    private void authenticate(@NonNull String username, @NonNull String password) throws Exception {
        logger.info("Authenticating User");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException de) {
            logger.error("User not active");
        } catch (BadCredentialsException be) {
            logger.error("Invalid Credential " + be);
            throw new Exception("Invalid credentials", be);
        }
    }
}
