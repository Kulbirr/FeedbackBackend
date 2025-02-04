package com.example.consumerFeedback.consumerFeedback.Model;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    private String feedback;

    private LocalDate feedbackCreatedDate;

    private LocalDate feedbackUpdatedDate;

    @ManyToOne()
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "topicId", nullable = false)
    private Topic topic;

}
