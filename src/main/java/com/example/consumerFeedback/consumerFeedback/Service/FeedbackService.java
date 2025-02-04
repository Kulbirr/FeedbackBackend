package com.example.consumerFeedback.consumerFeedback.Service;

import com.example.consumerFeedback.consumerFeedback.DTO.EditFeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.DTO.FeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.Model.Feedback;
import com.example.consumerFeedback.consumerFeedback.Response.FeedBackResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    String createFeedback(FeedbackDTO feedbackDTO);

    String editFeedback(Long id, EditFeedbackDTO editFeedbackDTO);

    String deleteFeedback(Long id);

    ResponseEntity<List<FeedBackResponse>> getAllFeedbacks();

    ResponseEntity<List<String>> getAllTopics();
}
