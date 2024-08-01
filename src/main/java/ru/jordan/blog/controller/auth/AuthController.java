package ru.jordan.blog.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jordan.blog.dto.request.LoginRequest;
import ru.jordan.blog.dto.request.SignupRequest;
import ru.jordan.blog.dto.response.MessageResponse;
import ru.jordan.blog.dto.response.UserInfoResponse;
import ru.jordan.blog.facade.auth.AuthFacade;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        UserInfoResponse userInfoResponse = authFacade.authenticateUser(loginRequest);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
                .body(userInfoResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        MessageResponse messageResponse = authFacade.registerUser(signUpRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        //ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
                .body(new MessageResponse("You've been signed out!"));
    }
}
