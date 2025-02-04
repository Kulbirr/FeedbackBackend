package com.example.consumerFeedback.consumerFeedback.DTO;

import com.example.consumerFeedback.consumerFeedback.ENUM.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
