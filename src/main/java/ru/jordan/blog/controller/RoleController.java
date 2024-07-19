package ru.jordan.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.jordan.blog.model.user.Role;
import ru.jordan.blog.service.RoleService;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private RoleService roleService;


    @PostMapping({"/createNewRole"})
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE)
                .body(roleService.createNewRole(role));
    }
}
