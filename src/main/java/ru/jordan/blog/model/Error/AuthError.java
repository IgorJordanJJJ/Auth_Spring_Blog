package ru.jordan.blog.model.Error;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AuthError {
    private UUID id;
    private int status;
    private String error;
    private String message;
    private String path;
    private OffsetDateTime datetime;
}
