package com.example.consumerFeedback.consumerFeedback.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFeedbackResponse {
    private Long feedbackId;
    private String userName;
    private String feedback;
    private String topic;
    private LocalDate createdDate;
    private LocalDate editedDate;
}
