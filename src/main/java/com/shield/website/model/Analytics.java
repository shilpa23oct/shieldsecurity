package com.shield.website.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analytics")
public class Analytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "visitor_ip")
    private String visitorIp;
    
    @Column(name = "user_agent")
    private String userAgent;
    
    @Column(name = "page_url")
    private String pageUrl;
    
    @Column(name = "referrer")
    private String referrer;
    
    @Column(name = "visit_time")
    private LocalDateTime visitTime;
    
    @Column(name = "session_id")
    private String sessionId;
    
    public Analytics() {}
    
    public Analytics(String visitorIp, String userAgent, String pageUrl, String referrer, String sessionId) {
        this.visitorIp = visitorIp;
        this.userAgent = userAgent;
        this.pageUrl = pageUrl;
        this.referrer = referrer;
        this.sessionId = sessionId;
        this.visitTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVisitorIp() { return visitorIp; }
    public void setVisitorIp(String visitorIp) { this.visitorIp = visitorIp; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    
    public String getPageUrl() { return pageUrl; }
    public void setPageUrl(String pageUrl) { this.pageUrl = pageUrl; }
    
    public String getReferrer() { return referrer; }
    public void setReferrer(String referrer) { this.referrer = referrer; }
    
    public LocalDateTime getVisitTime() { return visitTime; }
    public void setVisitTime(LocalDateTime visitTime) { this.visitTime = visitTime; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
}