package com.example.demo.models.repos;

import java.util.Optional;

import com.example.demo.models.entities.AppUserRole;
import com.example.demo.models.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(AppUserRole name);
}
