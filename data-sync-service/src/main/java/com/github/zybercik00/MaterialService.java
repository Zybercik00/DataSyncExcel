package com.github.zybercik00;

import com.github.zybercik00.entity.process.Material;
import com.github.zybercik00.repository.proces.MaterialRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepo repository;

    public Material getMaterial(String lot) {
        return repository.findByLot(lot)
                .orElseGet(() -> repository.save(Material.builder()
                        .lot(lot)
                        .build()));
    }
}
