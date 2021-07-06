package com.example.demo.utils;

import java.util.Optional;

import com.example.demo.models.entities.AppUser;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    // mengambil email yang sedang login
    // memberitahu jpa siapa yang sedang login
    @Override
    public Optional<String> getCurrentAuditor() {
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }

}
