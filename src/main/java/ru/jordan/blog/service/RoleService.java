package ru.jordan.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jordan.blog.model.user.Role;
import ru.jordan.blog.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
