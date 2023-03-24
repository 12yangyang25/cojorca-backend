package com.example.cojorca.repository;

import com.example.cojorca.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findByLoginId(String loginId);
}
