package com.example.demo.Controllers;

import com.example.demo.Models.dto.MailDTO;
import com.example.demo.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api2")
public class EmailController {
    private EmailService emailService;

    @Autowired
    EmailController(EmailService sendEmail) {
        this.emailService = sendEmail;
    }
    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody List<String> list){
        emailService.sendEmail(new MailDTO("isaias.jasso@gmail.com", list.get(0), "", "", "Employee Reimbursement Request", "Your Reimbursement Request for the amount $" +
                list.get(1) + " has been " + list.get(2) + ".", ""));

        return ResponseEntity.accepted().build();
    }
}
