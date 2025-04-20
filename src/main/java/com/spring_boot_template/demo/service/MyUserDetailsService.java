package com.spring_boot_template.demo.service;

import com.spring_boot_template.demo.model.User;
import com.spring_boot_template.demo.model.UserPrincipal;
import com.spring_boot_template.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error(String.format("User %s not found", username));
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new UserPrincipal(user);
    }
}
