package ru.jordan.blog.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(description = "Имя пользователя")
    private String userName;

    private String firstName;
    private String lastName;

    //    @NotBlank
//    @Size(max = 50)
//    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Schema(description = "Признак того, что учетная запись не просрочена")
    private Boolean accountNonExpired;

    @NotBlank
    @Schema(description = "Признак того, что учетная запись не заблокирована")
    private Boolean accountNonLocked;

    @NotBlank
    @Schema(description = "Признак того, что учетные данные не просрочены")
    private Boolean credentialsNonExpired;

    @NotBlank
    @Schema(description = "Признак того, что учетная запись включена")
    private Boolean enabled;
}
