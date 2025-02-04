package com.example.consumerFeedback.consumerFeedback.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    private String topic;

    @ManyToMany()
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList = new ArrayList<>();

}
