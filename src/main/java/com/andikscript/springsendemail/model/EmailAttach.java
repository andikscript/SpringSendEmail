package com.andikscript.springsendemail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmailAttach {

    @NotNull
    private String[] received;

    @NotBlank
    private String message;

    @NotBlank
    private String subject;

    @NotBlank
    private String attachment;

    public String[] getReceived() {
        return received;
    }

    public void setReceived(String[] received) {
        this.received = received;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
