package com.example.demo.models.repos;

import com.example.demo.models.entities.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AppUserRepo extends PagingAndSortingRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
