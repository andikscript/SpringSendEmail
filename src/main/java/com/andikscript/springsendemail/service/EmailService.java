package com.andikscript.springsendemail.service;

import com.andikscript.springsendemail.model.Email;
import com.andikscript.springsendemail.model.EmailAttach;

public interface EmailService {

    void sendEmail(Email email) throws Exception;

    void sendEmailWithAttach(EmailAttach email) throws Exception;
}
