package com.andikscript.springsendemail.service;

import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;
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

    @Override
    public String sendEmail(Email email) throws Exception {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getReceived());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent success";
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public String sendEmailWithAttach(EmailAttach email) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getReceived());
            mimeMessageHelper.setText(email.getMessage());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            javaMailSender.send(mimeMessage);
            return "Mail sent success";
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
