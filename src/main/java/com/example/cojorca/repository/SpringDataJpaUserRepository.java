package com.example.cojorca.repository;

import com.example.cojorca.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<User, Long>, UserRepository{
    @Override
    Optional<User> findByLoginId(String loginId);
}
