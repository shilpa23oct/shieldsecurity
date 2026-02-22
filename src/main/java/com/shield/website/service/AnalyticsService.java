package com.shield.website.service;

import com.shield.website.model.Analytics;
import com.shield.website.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    
    @Autowired
    private AnalyticsRepository analyticsRepository;
    
    public void trackVisit(HttpServletRequest request) {
        String visitorIp = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String pageUrl = request.getRequestURL().toString();
        String referrer = request.getHeader("Referer");
        String sessionId = request.getSession().getId();
        
        Analytics analytics = new Analytics(visitorIp, userAgent, pageUrl, referrer, sessionId);
        analyticsRepository.save(analytics);
    }
    
    public Map<String, Object> getAnalytics(int days) {
        LocalDateTime since = LocalDateTime.now().minusDays(days);
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalVisits", analyticsRepository.countTotalVisits(since));
        stats.put("uniqueVisitors", analyticsRepository.countUniqueVisitors(since));
        stats.put("uniqueSessions", analyticsRepository.countUniqueSessions(since));
        stats.put("topReferrers", analyticsRepository.getTopReferrers(since));
        
        return stats;
    }
    
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return xForwardedForHeader.split(",")[0];
        }
    }
}