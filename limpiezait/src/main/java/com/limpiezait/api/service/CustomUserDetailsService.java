package com.limpiezait.api.service;
import com.limpiezait.api.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final List<User> users = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        users.add(new User(1L, "user",
                passwordEncoder.encode("password"),
                List.of("ROLE_USER")));
        users.add(new User(2L, "admin",
                passwordEncoder.encode("admin123"),
                List.of("ROLE_USER", "ROLE_ADMIN")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }

    public User register(String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            throw new RuntimeException("El usuario ya existe");
        }
        User newUser = new User(
                (long) (users.size() + 1),
                username,
                passwordEncoder.encode(password),
                List.of("ROLE_USER")
        );
        users.add(newUser);
        return newUser;
    }
}