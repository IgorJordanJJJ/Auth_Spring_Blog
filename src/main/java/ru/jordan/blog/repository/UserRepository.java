package ru.jordan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jordan.blog.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
