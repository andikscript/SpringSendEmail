package com.andikscript.springsendemail.service;

import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;

public interface EmailService {

    String sendEmail(Email email) throws Exception;

    String sendEmailWithAttach(EmailAttach email) throws Exception;
}
