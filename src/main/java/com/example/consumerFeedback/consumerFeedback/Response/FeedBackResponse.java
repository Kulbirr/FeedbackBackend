package com.example.consumerFeedback.consumerFeedback.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackResponse {
    private Long feedbackId;
    private String feedback;
    private LocalDate feedbackCreateDate;
    private LocalDate feedBackEditedDate;
}
