package com.example.consumerFeedback.consumerFeedback.Service;


import com.example.consumerFeedback.consumerFeedback.Configuration.JWTProvider;
import com.example.consumerFeedback.consumerFeedback.DTO.LoginDTO;
import com.example.consumerFeedback.consumerFeedback.DTO.UserDto;
import com.example.consumerFeedback.consumerFeedback.ENUM.Role;
import com.example.consumerFeedback.consumerFeedback.Exceptions.InvalidCredentialsException;
import com.example.consumerFeedback.consumerFeedback.Exceptions.UserAlreadyExistsException;
import com.example.consumerFeedback.consumerFeedback.Model.User;
import com.example.consumerFeedback.consumerFeedback.Repository.UserRepository;
import com.example.consumerFeedback.consumerFeedback.Response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(User user) throws UserAlreadyExistsException{
        Optional<User> optionalUser = userRepository.findByuserName(user.getUserName());
        Optional<User> optionalUser1 = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent() && optionalUser1.isEmpty()) {
            throw new UserAlreadyExistsException("User with given username already exists.");
        } else if(optionalUser1.isPresent() && optionalUser.isEmpty()) {
            throw new UserAlreadyExistsException("User with given email already exists.");
        } else if(optionalUser.isPresent() && optionalUser1.isPresent()) {
            throw new UserAlreadyExistsException("You already have an account. login?");
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setRole(Role.USER);
//        enCoding the password before saving
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        // Now, we pass the user's role as a GrantedAuthority when creating Authentication
        String role = user.getRole().name();  // Get the role from newUser


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

//        Generating JWT token
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword(), authorities);
        String token = JWTProvider.generateToken(authentication);

        return new AuthResponse(token,role, "Account created successfully");

    }

    @Override
    public AuthResponse login(LoginDTO loginDTO) throws InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findByuserName(loginDTO.getUserName());

        if(userOptional.isEmpty()) throw new InvalidCredentialsException("Invalid username.");

        User authenticateUser = userOptional.get();

        if(!passwordEncoder.matches(loginDTO.getPassword(), authenticateUser.getPassword())){
            throw new InvalidCredentialsException("Incorrect password.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = authenticateUser.getRole();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.name())); // Add the role as an authority
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticateUser.getEmail(), authenticateUser.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JWTProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, role.name(),  "logged in Successfully.");

        return authResponse ;


    }
}
