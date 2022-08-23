package com.andikscript.springsendemail.controller;

import com.andikscript.springsendemail.message.ResponseMessage;
import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;
import com.andikscript.springsendemail.service.EmailService;
import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.annotation.NonNull;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    private EmailService emailService;

    private final RqueueMessageEnqueuer rqueueMessageEnqueuer;

    public EmailController(EmailService emailService, RqueueMessageEnqueuer rqueueMessageEnqueuer) {
        this.emailService = emailService;
        this.rqueueMessageEnqueuer = rqueueMessageEnqueuer;
    }

    @PostMapping(value = "/sendMail")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody  Email email) {
        rqueueMessageEnqueuer.enqueue("email-queue", email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Please check your inbox");
    }

    @PostMapping(value = "/sendMailAttach")
    public ResponseEntity<?> sendEmailAttach(@Valid @RequestBody EmailAttach email) throws Exception {
        rqueueMessageEnqueuer.enqueue("email-queue-attach", email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Please check your inbox"));
    }
}
