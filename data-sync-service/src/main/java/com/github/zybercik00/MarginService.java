package com.github.zybercik00;

import com.github.zybercik00.entity.process.Margin;
import com.github.zybercik00.repository.proces.MarginRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarginService {

    private final MarginRepo repository;
    public Margin getByName(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(Margin
                        .builder()
                        .name(name)
                        .build()));
    }
}
