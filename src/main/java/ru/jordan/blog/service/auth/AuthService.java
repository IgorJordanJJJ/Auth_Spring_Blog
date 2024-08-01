package ru.jordan.blog.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jordan.blog.dto.request.LoginRequest;
import ru.jordan.blog.dto.request.SignupRequest;
import ru.jordan.blog.dto.response.UserInfoResponse;
import ru.jordan.blog.model.user.User;
import ru.jordan.blog.security.jwt.JwtUtils;
import ru.jordan.blog.security.service.UserDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    AuthenticationManager authenticationManager;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    public UserInfoResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return UserInfoResponse.builder()
                .id(userDetails.getId())
                .userName(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .token(jwt)
                .type("Bearer")
                .build();

    }


    public User createUser(SignupRequest signUpRequest){
        return User.builder()
                .userName(signUpRequest.getUserName())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .accountNonExpired(signUpRequest.getAccountNonExpired())
                .accountNonLocked(signUpRequest.getAccountNonLocked())
                .credentialsNonExpired(signUpRequest.getAccountNonExpired())
                .enabled(signUpRequest.getEnabled())
                .build();
    }
}
