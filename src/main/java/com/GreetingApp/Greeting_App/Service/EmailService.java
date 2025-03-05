package com.GreetingApp.Greeting_App.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String name, String subject, String messageBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("Hello " + name + ",\n\n" + messageBody + "\n\nThank you for joining us.", true);
            helper.setFrom("nomicycapg@gmail.com");

            mailSender.send(message);
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }

    public void sendRegistrationEmail(String toEmail, String name) {
        sendEmail(toEmail, name, "Welcome to Greeting App 🎉", "Your registration was successful!");
    }

    public void sendLoginNotification(String toEmail, String name) {
        String messageBody = "You have successfully logged in!\n\nIf this wasn't you, please reset your password immediately.";
        sendEmail(toEmail, name, "‼️Login Alert‼️", messageBody);
    }
}
