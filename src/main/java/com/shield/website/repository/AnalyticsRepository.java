package com.shield.website.repository;

import com.shield.website.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
    
    @Query("SELECT COUNT(DISTINCT a.visitorIp) FROM Analytics a WHERE a.visitTime >= ?1")
    Long countUniqueVisitors(LocalDateTime since);
    
    @Query("SELECT COUNT(a) FROM Analytics a WHERE a.visitTime >= ?1")
    Long countTotalVisits(LocalDateTime since);
    
    @Query("SELECT COUNT(DISTINCT a.sessionId) FROM Analytics a WHERE a.visitTime >= ?1")
    Long countUniqueSessions(LocalDateTime since);
    
    @Query("SELECT a.referrer, COUNT(a) FROM Analytics a WHERE a.visitTime >= ?1 AND a.referrer IS NOT NULL GROUP BY a.referrer ORDER BY COUNT(a) DESC")
    List<Object[]> getTopReferrers(LocalDateTime since);
}