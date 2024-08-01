package ru.jordan.blog.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.jordan.blog.model.Error.AuthError;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        UUID errorId = UUID.randomUUID();
        logger.error("Unauthorized error {} processing request {}", errorId, authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        AuthError error = createError(request, authException, errorId, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), error);
    }

    private AuthError createError(HttpServletRequest request, Exception ex, UUID errorId, int status, String error) {
        AuthError authError = new AuthError();
        authError.setId(errorId);
        authError.setStatus(status);
        authError.setError(error);
        authError.setMessage(ex.getMessage());
        authError.setPath(request.getServletPath());
        authError.setDatetime(OffsetDateTime.now());
        return authError;
    }
}