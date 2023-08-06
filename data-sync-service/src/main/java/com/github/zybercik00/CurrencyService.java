package com.github.zybercik00;

import com.github.zybercik00.entity.process.Currency;
import com.github.zybercik00.repository.proces.CurrencyRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepo repository;

    public Currency getByCode(String name) {
        return repository.findByCode(name)
                .orElseGet(() -> repository.save(Currency
                        .builder()
                        .code(name)
                        .build()));
    }
}