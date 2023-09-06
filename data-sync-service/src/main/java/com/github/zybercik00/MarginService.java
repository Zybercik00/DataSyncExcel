package com.github.zybercik00;

import com.github.zybercik00.domain.process.Margin;
import com.github.zybercik00.repository.process.MarginRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarginService {

    private final MarginRepo repository;
    public Margin getByName(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(new Margin(name)));
    }
}
