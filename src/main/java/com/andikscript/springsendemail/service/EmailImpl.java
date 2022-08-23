package com.andikscript.springsendemail.service;

import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;
import com.github.sonus21.rqueue.annotation.RqueueListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

@Service
public class EmailImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RqueueListener(value = "email-queue")
    @Override
    public void sendEmail(Email email) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email.getReceived());
        mailMessage.setText(email.getMessage());
        mailMessage.setSubject(email.getSubject());
        javaMailSender.send(mailMessage);
    }

    @RqueueListener(value = "email-queue-attach")
    @Override
    public void sendEmailWithAttach(EmailAttach email) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(email.getReceived());
        mimeMessageHelper.setText(email.getMessage());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

        javaMailSender.send(mimeMessage);
    }
}
