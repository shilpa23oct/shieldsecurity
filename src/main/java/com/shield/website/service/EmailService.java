package com.shield.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.shield.website.model.Enquiry;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    public void sendEnquiryNotification(Enquiry enquiry) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(fromEmail);
            message.setSubject("New Enquiry - Shield Security Services");
            message.setText(
                "New Enquiry Received:\n\n" +
                "Name: " + enquiry.getName() + "\n" +
                "Phone: " + enquiry.getPhone() + "\n" +
                "Service: " + enquiry.getService() + "\n" +
                "Message: " + enquiry.getMessage() + "\n\n" +
                "Submitted at: " + enquiry.getSubmittedAt()
            );
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }
    }
}
