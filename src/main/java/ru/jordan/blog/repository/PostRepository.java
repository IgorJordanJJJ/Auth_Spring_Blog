package ru.jordan.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.jordan.blog.model.blog.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByTitle(String title);
    boolean existsByUuid(UUID uuid);
    Optional<Post> findByUuid(UUID uuid);

    @Query("SELECT p FROM Post p")
    Optional<Slice<Post>> findAllSlice(Pageable pageable);

    Optional<Page<Post>> findAllByTitleLikeIgnoreCase(String titleLike, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title like %:title%")
    Optional<Page<Post>> findAllByTitleJpql(@Param("title") String title, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM POST WHERE TITLE LIKE %?1%",
            countQuery = "SELECT count(*) FROM POST WHERE TITLE LIKE %?1%"
    )
    Optional<Page<Post>> findAllByTitleNative(String title, Pageable pageable);

    void deleteByUuid(UUID uuid);

    List<Post> findByTitle(String title);
}
