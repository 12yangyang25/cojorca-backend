package com.example.cojorca.controller;

import com.example.cojorca.DTO.CafeInfoDTO;
import com.example.cojorca.domain.Cafe;
import com.example.cojorca.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CafeController {

    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/cafe")
    public List<Cafe> getCafeList() {
        return cafeService.findAllCafe();
    }

    @PostMapping("/cafe")
    public Cafe createCafe(@RequestBody CafeInfoDTO cafeInfoDTO) {
        LocalDate cur_date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted_date = cur_date.format(formatter);

        Cafe cafe = new Cafe(cafeInfoDTO.getUser(), cafeInfoDTO.getCafeName(), cafeInfoDTO.getAddress(), cafeInfoDTO.getNaverPlaceId(), cafeInfoDTO.getLat(), cafeInfoDTO.getLng(), String.join(",", cafeInfoDTO.getTags()), cafeInfoDTO.getImgUrls(), formatted_date);
        cafeService.postCafe(cafe);
        return cafe;
    }
    @GetMapping("/cafe/{id}")
    public Optional<Cafe> getCafeInfo(@PathVariable Long id) {
        return cafeService.findCafe(id);
    }

    @DeleteMapping("/cafe/{id}")
    public void deleteCafe(@PathVariable Long id) {
        cafeService.removeCafe(id);
    }
}
