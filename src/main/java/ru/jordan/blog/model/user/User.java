package ru.jordan.blog.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Schema(description = "Пользователь")
@Table(name = "user"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Имя пользователя", example = "1")
    private Long id;
    @Schema(description = "Логин пользователя")
    @NotBlank
    @Size(max = 20)
    private String userName;
    @Schema(description = "Имя пользователя")
    private String firstName;
    @Schema(description = "Фамилия пользователя")
    private String lastName;
    @NotBlank
    @Size(max = 120)
    @Schema(description = "Пароль пользователя")
    private String password;
    //    @NotBlank
//    @Size(max = 50)
//    @Email
    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> roles = new HashSet<>();

    @Schema(description = "Признак того, что учетная запись не просрочена")
    private Boolean accountNonExpired;
    @Schema(description = "Признак того, что учетная запись не заблокирована")
    private Boolean accountNonLocked;
    @Schema(description = "Признак того, что учетные данные не просрочены")
    private Boolean credentialsNonExpired;
    @Schema(description = "Признак того, что учетная запись включена")
    private Boolean enabled;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userName = " + userName + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "accountNonExpired = " + accountNonExpired + ", " +
                "accountNonLocked = " + accountNonLocked + ", " +
                "credentialsNonExpired = " + credentialsNonExpired + ", " +
                "enabled = " + enabled + ")";
    }
}
