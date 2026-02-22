package com.shield.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.shield.website.model.Enquiry;

@Service
public class WhatsAppService {
    
    @Value("${whatsapp.phone}")
    private String whatsappPhone;
    
    public String generateWhatsAppUrl(Enquiry enquiry) {
        String message = String.format(
            "*New Enquiry from Website*\n\n" +
            "*Name:* %s\n" +
            "*Phone:* %s\n" +
            "*Service:* %s\n" +
            "*Message:* %s",
            enquiry.getName(),
            enquiry.getPhone(),
            enquiry.getService(),
            enquiry.getMessage()
        );
        
        String encodedMessage = message.replace("\n", "%0A")
                                      .replace(" ", "%20")
                                      .replace("*", "%2A");
        
        return "https://wa.me/" + whatsappPhone + "?text=" + encodedMessage;
    }
}
