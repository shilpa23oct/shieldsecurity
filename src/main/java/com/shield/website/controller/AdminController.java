package com.shield.website.controller;

import com.shield.website.service.EnquiryService;
import com.shield.website.service.FeedbackService;
import com.shield.website.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private EnquiryService enquiryService;
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/login")
    public String login() {
        return "admin-login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("enquiries", enquiryService.getAllEnquiries());
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        
        // Add analytics data
        Map<String, Object> analytics = analyticsService.getAnalytics(30);
        model.addAttribute("analytics", analytics);
        
        return "admin-dashboard";
    }
    
    @PostMapping("/enquiry/{id}/status")
    @ResponseBody
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        enquiryService.updateStatus(id, status);
        return "success";
    }
    
    @DeleteMapping("/enquiry/{id}")
    @ResponseBody
    public String deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return "success";
    }
    
    @DeleteMapping("/feedback/{id}")
    @ResponseBody
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "success";
    }
}
