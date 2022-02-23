package com.example.demo.Models.dto;

import lombok.*;

//    you just need to call the sendEmail method from your autowired Service Implementation and that's it
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailDTO {

    private String emailFrom;
    private String emailTo;
    private String emailCc;
    private String emailBcc;
    private String emailSubject;
    private String emailContent;
    private String contentType;

}