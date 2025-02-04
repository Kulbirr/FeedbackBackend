package com.example.consumerFeedback.consumerFeedback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor

public class FeedbackDTO {
    private String feedback;
    private String topic;
}
