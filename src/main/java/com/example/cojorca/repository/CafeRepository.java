package com.example.cojorca.repository;

import com.example.cojorca.domain.Cafe;

import java.util.List;
import java.util.Optional;

public interface CafeRepository {
    Cafe save(Cafe cafe);

    Optional<Cafe> findById(Long id);

    List<Cafe> findAll();

    void deleteById(Long id);

    Optional<Cafe> findByCafeName(String cafeName);
}
