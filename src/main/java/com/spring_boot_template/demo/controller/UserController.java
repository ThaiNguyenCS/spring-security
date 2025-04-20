package com.spring_boot_template.demo.controller;

import com.spring_boot_template.demo.model.User;
import com.spring_boot_template.demo.model.UserPrincipal;
import com.spring_boot_template.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String result = userService.verifyUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDetails> getProfile(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        return ResponseEntity.ok().body(userDetails);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/for-users")
    public ResponseEntity<String> getForUsers() {
        return ResponseEntity.ok().body("OK");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/for-admin")
    public ResponseEntity<String> getForAdmins() {
        return ResponseEntity.ok().body("OK");
    }
}
