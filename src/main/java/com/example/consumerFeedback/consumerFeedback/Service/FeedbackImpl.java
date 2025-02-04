package com.example.consumerFeedback.consumerFeedback.Service;

import com.example.consumerFeedback.consumerFeedback.DTO.EditFeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.DTO.FeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.Model.Feedback;
import com.example.consumerFeedback.consumerFeedback.Model.Topic;
import com.example.consumerFeedback.consumerFeedback.Model.User;
import com.example.consumerFeedback.consumerFeedback.Repository.FeedbackRepository;
import com.example.consumerFeedback.consumerFeedback.Repository.TopicRepository;
import com.example.consumerFeedback.consumerFeedback.Repository.UserRepository;
import com.example.consumerFeedback.consumerFeedback.Response.FeedBackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackImpl implements FeedbackService{
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final TopicRepository topicRepository;

    @Override
    public String createFeedback(FeedbackDTO feedbackDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).get();
        Topic topic = topicRepository.getTopicByTopic(feedbackDTO.getTopic());
        Feedback feedback = new Feedback();

        feedback.setFeedback(feedbackDTO.getFeedback());
        feedback.setFeedbackCreatedDate(LocalDate.now());
        feedback.setUser(user);
        feedback.setTopic(topic);
        feedbackRepository.save(feedback);
        return "Feedback added successfully";
    }

    @Override
    public String editFeedback(Long id, EditFeedbackDTO editFeedbackDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        User user = userRepository.findByEmail(email).get();

        Feedback feedback = feedbackRepository.findById(id).get();

        feedback.setFeedback(editFeedbackDTO.getFeedback());
        feedback.setFeedbackUpdatedDate(editFeedbackDTO.getEditDate());
        feedbackRepository.save(feedback);


        return "Feedback edited successfully";
    }

    @Override
    public String deleteFeedback(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Find the user by email
        User user = userRepository.findByEmail(email).get();

        // Finding the Feedback by id and ensure it belongs to the authenticated user
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback Not Found"));

//        checking if the feedback belongs to the user
        if (!feedback.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized: You cannot delete this feedback");
        }

        feedbackRepository.delete(feedback);

        return "Feedback deleted successfully";
    }

    @Override
    public ResponseEntity<List<FeedBackResponse>> getAllFeedbacks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        usually we get username from here but in my jwt I am returning email instead of userName
        String email = authentication.getName();
//        System.out.println(email);

        User user = userRepository.findByEmail(email).get();

        List<Feedback> feedbackList = feedbackRepository.findAllFeedbacksByUserId(user.getUserId());

//        fetching all the todos and returning the todoResponse
        List<FeedBackResponse> feedBackResponseList = new ArrayList<>();
        for(Feedback feedback : feedbackList){
            FeedBackResponse feedBackResponse = new FeedBackResponse(feedback.getFeedbackId(), feedback.getFeedback(),
                                                     feedback.getFeedbackCreatedDate(), feedback.getFeedbackUpdatedDate());
            feedBackResponseList.add(feedBackResponse);
        }

        return new ResponseEntity(feedBackResponseList, HttpStatus.OK);
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
