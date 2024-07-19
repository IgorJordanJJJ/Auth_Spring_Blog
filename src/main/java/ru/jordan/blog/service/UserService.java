package ru.jordan.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jordan.blog.model.user.User;
import ru.jordan.blog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerNewUser(User user) {
        return userRepository.save(user);
    }


}
