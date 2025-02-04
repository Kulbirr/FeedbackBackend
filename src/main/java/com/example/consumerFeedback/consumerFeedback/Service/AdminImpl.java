package com.example.consumerFeedback.consumerFeedback.Service;

import com.example.consumerFeedback.consumerFeedback.Model.Feedback;
import com.example.consumerFeedback.consumerFeedback.Model.Topic;
import com.example.consumerFeedback.consumerFeedback.Model.User;
import com.example.consumerFeedback.consumerFeedback.Repository.FeedbackRepository;
import com.example.consumerFeedback.consumerFeedback.Repository.TopicRepository;
import com.example.consumerFeedback.consumerFeedback.Repository.UserRepository;
import com.example.consumerFeedback.consumerFeedback.Response.AdminFeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminImpl implements AdminService{
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createTopic(String topic) throws Exception {

        List<Topic> topicList = topicRepository.findAll();

        for(Topic topicc : topicList){
            if(topicc.getTopic().toLowerCase().equals(topic.toLowerCase())){
                throw new Exception("Topic Already exists");
            }
        }
        Topic topic1 = new Topic();
        topic1.setTopic(topic);
        topicRepository.save(topic1);
        return "Topic saved Successfully";
    }

    @Override
    public String deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback Not Found"));

        feedbackRepository.delete(feedback);

        return "Feedback deleted successfully";
    }

    @Override
    public ResponseEntity<List<AdminFeedbackResponse>> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();

        List<AdminFeedbackResponse> adminFeedbackResponseList = new ArrayList<>();
        for(Feedback feedback : feedbackList){
            AdminFeedbackResponse adminFeedbackResponse = new AdminFeedbackResponse();
            adminFeedbackResponse.setFeedback(feedback.getFeedback());
            adminFeedbackResponse.setTopic(feedback.getTopic().getTopic());
            adminFeedbackResponse.setCreatedDate(feedback.getFeedbackCreatedDate());
            adminFeedbackResponse.setUserName(feedback.getUser().getUserName());
            adminFeedbackResponse.setEditedDate(feedback.getFeedbackUpdatedDate());
            adminFeedbackResponse.setFeedbackId(feedback.getFeedbackId());
            adminFeedbackResponseList.add(adminFeedbackResponse);
        }
        return new ResponseEntity(adminFeedbackResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getAllTopics() {
        List<String> topicList = new ArrayList<>();
        List<Topic> topics = topicRepository.findAll();

        for(Topic topic : topics){
            String s = topic.getTopic();
            topicList.add(s);
        }
        return new ResponseEntity(topicList, HttpStatus.OK);
    }


}
