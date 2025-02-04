package com.example.consumerFeedback.consumerFeedback.Repository;

import com.example.consumerFeedback.consumerFeedback.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId")
    List<Feedback> findAllFeedbacksByUserId(@Param("userId") Long userId);

}
