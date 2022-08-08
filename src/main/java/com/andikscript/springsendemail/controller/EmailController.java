package com.andikscript.springsendemail.controller;

import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/sendMail")
    public String sendEmail(@RequestBody  Email email) {
        return emailService.sendEmail(email);
    }
}
