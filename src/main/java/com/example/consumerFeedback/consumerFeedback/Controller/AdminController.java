package com.example.consumerFeedback.consumerFeedback.Controller;

import com.example.consumerFeedback.consumerFeedback.Response.AdminFeedbackResponse;
import com.example.consumerFeedback.consumerFeedback.Service.AdminService;
import com.example.consumerFeedback.consumerFeedback.Service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PostMapping("/create-topic/{topic}")
    public ResponseEntity<String> createTopic(@PathVariable  String topic){
        try{
            String response = adminService.createTopic(topic);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            System.err.println("Error creating topic: " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-feedbacks")
    public ResponseEntity<List<AdminFeedbackResponse>> getAllFeedbacks(){
        return adminService.getAllFeedbacks();
    }

    @DeleteMapping("delete-feedback/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id){
        try{
            AdminController feedbackService;
            return new ResponseEntity(adminService.deleteFeedback(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-topics")
    public ResponseEntity<List<String>> getAllTopics(){
        return adminService.getAllTopics();
    }
}
