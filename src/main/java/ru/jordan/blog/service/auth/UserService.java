package ru.jordan.blog.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jordan.blog.dto.request.SignupRequest;
import ru.jordan.blog.model.user.User;
import ru.jordan.blog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerNewUser(User user) {
        return userRepository.save(user);
    }

    public void validateUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }
    }

}
