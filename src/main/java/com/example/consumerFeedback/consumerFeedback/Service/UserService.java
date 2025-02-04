package com.example.consumerFeedback.consumerFeedback.Service;

import com.example.consumerFeedback.consumerFeedback.DTO.LoginDTO;
import com.example.consumerFeedback.consumerFeedback.DTO.UserDto;
import com.example.consumerFeedback.consumerFeedback.Exceptions.InvalidCredentialsException;
import com.example.consumerFeedback.consumerFeedback.Exceptions.UserAlreadyExistsException;
import com.example.consumerFeedback.consumerFeedback.Model.User;
import com.example.consumerFeedback.consumerFeedback.Response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthResponse signup(User user) throws UserAlreadyExistsException;

    AuthResponse login(LoginDTO loginDTO) throws InvalidCredentialsException;


}
