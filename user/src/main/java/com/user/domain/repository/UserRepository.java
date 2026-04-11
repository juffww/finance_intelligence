package com.user.domain.repository;

import com.user.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Định nghĩa các contract thao
public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(UUID id);
    User save(User user);
    List<User> findAll();
    void deleteById(UUID id);
}
