package com.example.demo.Security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class EmployeeJWTRequestFilter  extends OncePerRequestFilter {

    @Autowired
    private EmployeeJWT employeeJWT;

    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;

    @Value("${react.config.url}")
    private String test;

    private static Logger logger = LoggerFactory.getLogger(EmployeeJWTRequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        final String requestUrl = request.getRequestURI();
        String username = null;
        String token = null;


        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            logger.info("Token is present " +  token);
            try {
                username = employeeJWT.getUsernameFromToken(token);
            } catch (IllegalArgumentException ae) {
                logger.debug("Unable to get token");
            } catch (ExpiredJwtException ee) {
                logger.debug("Token has Expired");
            } catch (Exception e){
                logger.debug("This is the error");
                System.out.println(e.getStackTrace());
            }
        } else {
            //logger.debug("This is the URL " + test);
            logger.warn("JWT does not begin with Bearer string on " + requestUrl);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(username);

            if(employeeJWT.validate(token, userDetails)) {
                logger.info("Token valid");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? Collections.emptyList() : userDetails.getAuthorities());
                System.out.println(userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);



    }
}
