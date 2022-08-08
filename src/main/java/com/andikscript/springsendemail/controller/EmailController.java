package com.andikscript.springsendemail.controller;

import com.andikscript.springsendemail.message.ResponseMessage;
import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;
import com.andikscript.springsendemail.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/sendMail")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody  Email email) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage(emailService.sendEmail(email)));
    }

    @PostMapping(value = "/sendMailAttach")
    public ResponseEntity<?> sendEmailAttach(@Valid @RequestBody EmailAttach email) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage(emailService.sendEmailWithAttach(email)));
    }
}
