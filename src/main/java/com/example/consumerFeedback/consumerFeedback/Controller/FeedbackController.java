package com.example.consumerFeedback.consumerFeedback.Controller;

import com.example.consumerFeedback.consumerFeedback.DTO.EditFeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.DTO.FeedbackDTO;
import com.example.consumerFeedback.consumerFeedback.Repository.FeedbackRepository;
import com.example.consumerFeedback.consumerFeedback.Response.FeedBackResponse;
import com.example.consumerFeedback.consumerFeedback.Service.AdminService;
import com.example.consumerFeedback.consumerFeedback.Service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback/")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<String> createFeedback(@RequestBody FeedbackDTO feedbackDTO){
        try{
            return new ResponseEntity(feedbackService.createFeedback(feedbackDTO), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editFeedback(@PathVariable Long id, @RequestBody EditFeedbackDTO editFeedbackDTO){
        try{
            return new ResponseEntity(feedbackService.editFeedback(id, editFeedbackDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<FeedBackResponse>> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id){
        try{
            return new ResponseEntity(feedbackService.deleteFeedback(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-topics")
    public ResponseEntity<List<String>> getAllTopics(){
        return adminService.getAllTopics();
    }

}
