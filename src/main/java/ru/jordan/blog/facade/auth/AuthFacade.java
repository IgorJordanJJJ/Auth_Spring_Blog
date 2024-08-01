package ru.jordan.blog.facade.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jordan.blog.dto.request.LoginRequest;
import ru.jordan.blog.dto.request.SignupRequest;
import ru.jordan.blog.dto.response.MessageResponse;
import ru.jordan.blog.dto.response.UserInfoResponse;
import ru.jordan.blog.model.user.Role;
import ru.jordan.blog.model.user.User;
import ru.jordan.blog.service.auth.AuthService;
import ru.jordan.blog.service.auth.RoleService;
import ru.jordan.blog.service.auth.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authenticate;
    private final UserService userService;
    private final RoleService roleService;

    public UserInfoResponse authenticateUser(LoginRequest loginRequest) {
        return authenticate.authenticateUser(loginRequest);
    }

    public MessageResponse registerUser(SignupRequest signUpRequest){
        userService.validateUser(signUpRequest);

        // Create new user's account
        User user = authenticate.createUser(signUpRequest);

        Set<Role> roles = roleService.createRole(signUpRequest);

        user.setRoles(roles);
        userService.registerNewUser(user);
        return new MessageResponse("User registered successfully!");
    }
}
