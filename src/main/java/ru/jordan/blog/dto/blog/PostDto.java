package ru.jordan.blog.dto.blog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import ru.jordan.blog.model.blog.Post;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность поста блога")
public class PostDto {

    @Schema(description = "Уникальный идентификатор поста", example = "1")
    private Long id;

    @Schema(description = "UUID поста", example = "c1f1a2b6-3f25-4e1b-91c1-789b1f63517b")
    private UUID uuid;

    @Schema(description = "Заголовок поста", example = "Мой первый пост")
    private String title;

    @Schema(description = "Содержимое поста", example = "Это содержимое моего первого поста.")
    private String content;

    @Schema(description = "Автор поста", example = "Иван Иванов")
    private String author;

    @Schema(description = "Дата и время создания поста", example = "2023-07-21T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "Дата и время последнего обновления поста", example = "2023-07-21T15:30:00")
    private LocalDateTime updatedAt;

//    @PrePersist
//    protected void onCreate() {
//        uuid = UUID.randomUUID();
//    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Post post = (Post) o;
        return getId() != null && Objects.equals(getId(), post.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
