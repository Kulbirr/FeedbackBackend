package com.example.consumerFeedback.consumerFeedback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class EditFeedbackDTO {
    private String feedback;

    private LocalDate editDate;
}