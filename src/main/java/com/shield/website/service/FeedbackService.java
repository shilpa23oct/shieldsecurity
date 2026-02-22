package com.shield.website.service;

import com.shield.website.model.Feedback;
import com.shield.website.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
    
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}