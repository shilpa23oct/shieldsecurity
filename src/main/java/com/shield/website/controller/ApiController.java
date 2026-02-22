package com.shield.website.controller;

import com.shield.website.model.Enquiry;
import com.shield.website.model.Feedback;
import com.shield.website.service.EnquiryService;
import com.shield.website.service.FeedbackService;
import com.shield.website.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private EnquiryService enquiryService;
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @PostMapping("/enquiry")
    public ResponseEntity<?> submitEnquiry(@RequestBody Enquiry enquiry) {
        Enquiry saved = enquiryService.saveEnquiry(enquiry);
        String whatsappUrl = enquiryService.getWhatsAppUrl(saved);
        return ResponseEntity.ok().body(new EnquiryResponse(saved.getId(), whatsappUrl));
    }
    
    @PostMapping("/feedback")
    public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
        Feedback saved = feedbackService.saveFeedback(feedback);
        return ResponseEntity.ok().body(new FeedbackResponse(saved.getId(), "Feedback submitted successfully"));
    }
    
    @PostMapping("/track")
    public ResponseEntity<?> trackVisit(HttpServletRequest request) {
        analyticsService.trackVisit(request);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics(@RequestParam(defaultValue = "30") int days) {
        return ResponseEntity.ok(analyticsService.getAnalytics(days));
    }
    
    static class EnquiryResponse {
        public Long id;
        public String whatsappUrl;
        
        public EnquiryResponse(Long id, String whatsappUrl) {
            this.id = id;
            this.whatsappUrl = whatsappUrl;
        }
    }
    
    static class FeedbackResponse {
        public Long id;
        public String message;
        
        public FeedbackResponse(Long id, String message) {
            this.id = id;
            this.message = message;
        }
    }
}
