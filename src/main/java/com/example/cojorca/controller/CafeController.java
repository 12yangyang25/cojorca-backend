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
    public List<Cafe> getCafeList(){
        return cafeService.findAllCafe();
    }
    @PostMapping("/cafe")
    public void createCafe(@RequestBody CafeInfoDTO cafeInfoDTO){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        Cafe cafe = new Cafe(cafeInfoDTO.getUser(), cafeInfoDTO.getCafeName(), cafeInfoDTO.getAddress(), String.join(",", cafeInfoDTO.getAdditionalInfo()), cafeInfoDTO.getImgUrls(), formattedDate);
        cafeService.postCafe(cafe);
    }
    @GetMapping("/cafe/{id}")
    public Optional<Cafe> getCafeInfo(@PathVariable Long id){
        return cafeService.findCafe(id);
    }

//    @PostMapping("/cafe/{id}")
//    public void updateCafeInfo(@PathVariable Long id, @RequestBody ){
//
//    }

    @DeleteMapping("/cafe/{id}")
    public void deleteCafe(@PathVariable Long id){
        cafeService.removeCafe(id);
    }
}
