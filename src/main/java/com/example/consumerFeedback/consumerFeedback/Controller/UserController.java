package com.example.consumerFeedback.consumerFeedback.Controller;
import com.example.consumerFeedback.consumerFeedback.DTO.LoginDTO;
import com.example.consumerFeedback.consumerFeedback.Exceptions.InvalidCredentialsException;
import com.example.consumerFeedback.consumerFeedback.Exceptions.UserAlreadyExistsException;
import com.example.consumerFeedback.consumerFeedback.Model.User;
import com.example.consumerFeedback.consumerFeedback.Response.AuthResponse;
import com.example.consumerFeedback.consumerFeedback.Service.UserService;
import lombok.RequiredArgsConstructor; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("register")
    public ResponseEntity<AuthResponse> signup(@RequestBody User user){
        try{
            AuthResponse authResponse = userService.signup(user);
            return new ResponseEntity(authResponse, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO loginDTO){
        try{
            return new ResponseEntity(userService.login(loginDTO), HttpStatus.OK);
        }catch (InvalidCredentialsException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
