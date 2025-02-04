package com.example.consumerFeedback.consumerFeedback.Service;

import com.example.consumerFeedback.consumerFeedback.Response.AdminFeedbackResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    String createTopic(String topic) throws Exception;

    String deleteFeedback(Long id);

    ResponseEntity<List<AdminFeedbackResponse>> getAllFeedbacks();

    ResponseEntity<List<String>> getAllTopics();
}
