package ru.jordan.blog.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserInfoResponse {
    private String token;
    private String type;
    private Long id;
    private String userName;
    private String email;
    private List<String> roles;

}
