package com.example.cojorca.repository;

import com.example.cojorca.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaCafeRepository extends JpaRepository<Cafe, Long>, CafeRepository {
    @Override
    Optional<Cafe> findByName(String name);
}
