package com.andikscript.springsendemail.service;

import com.andikscript.springsendemail.model.Email;

public interface EmailService {

    String sendEmail(Email email);
}
