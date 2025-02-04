package com.example.consumerFeedback.consumerFeedback.Repository;

import com.example.consumerFeedback.consumerFeedback.Model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("SELECT t FROM Topic t WHERE t.topic = :topic")
    Topic getTopicByTopic(String topic);
}
