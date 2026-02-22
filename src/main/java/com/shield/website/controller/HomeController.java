package com.shield.website.controller;

import com.shield.website.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        analyticsService.trackVisit(request);
        return "index";
    }
    
    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
