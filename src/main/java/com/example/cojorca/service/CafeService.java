package com.example.cojorca.service;

import com.example.cojorca.domain.Cafe;
import com.example.cojorca.repository.CafeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class CafeService {

    private final CafeRepository cafeRepository;
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public void postCafe(Cafe cafe){
        cafeRepository.findByName(cafe.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 카페입니다.");
        });

        cafeRepository.save(cafe);
    }

    public Optional<Cafe> findCafe(Long cafeId){
        return cafeRepository.findById(cafeId);
    }

    public List<Cafe> findAllCafe(){
        return cafeRepository.findAll();
    }

    public void removeCafe(Long cafeId){
        cafeRepository.deleteById(cafeId);
    }
}
