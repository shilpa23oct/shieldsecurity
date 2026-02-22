package com.shield.website.service;

import com.shield.website.model.Enquiry;
import com.shield.website.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnquiryService {
    
    @Autowired
    private EnquiryRepository enquiryRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private WhatsAppService whatsAppService;
    
    public Enquiry saveEnquiry(Enquiry enquiry) {
        Enquiry saved = enquiryRepository.save(enquiry);
        
        // Send email notification
        emailService.sendEnquiryNotification(saved);
        
        return saved;
    }
    
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAllByOrderBySubmittedAtDesc();
    }
    
    public Enquiry updateStatus(Long id, String status) {
        Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(() -> new RuntimeException("Enquiry not found"));
        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }
    
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
    }
    
    public String getWhatsAppUrl(Enquiry enquiry) {
        return whatsAppService.generateWhatsAppUrl(enquiry);
    }
}
