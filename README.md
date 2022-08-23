# Spring Send Email
Simple send emails (gmail) via SMTP using the JavaMail Library on Spring Boot and use Rqueue with Redis for fast response when user request

## How to use
### Setting username 
- spring.mail.username=... -> on src/main/resources/application.properties with username gmail
### Setting password 
- spring.mail.password=... -> on src/main/resources/application.properties with password generate gmail and need 2-step verification to be enabled for your account
```
Login to Gmail 
    -> Manage your Google Account 
        -> Security 
            -> App Passwords 
                -> Provide your login password 
                    -> Select app with a custom name 
                        -> Click on Generate
```

## API
Methods | Url | Action |
--- | --- | --- |
| POST | /api/email/sendMail | send email without attachment |
| POST | /api/email/sendMailAttach | send email with attachment |

## Format JSON Send

### Without Attachment
```
{
    "received": [
        "nameemail@gmail.com"
    ],
    "subject": "subject",
    "message": "message"
}
```

### With Attachment
```
{
    "received": [
        "nameemail1@gmail.com",
        "nameemail2@gmail.com"
    ],
    "subject": "subject",
    "message": "message"
    "attachment": "path location file"
}
```
