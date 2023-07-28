package com.github.zybercik00;

import com.github.zybercik00.domain.proces.Currency;
import com.github.zybercik00.domain.proces.Margin;
import com.github.zybercik00.repository.proces.CurrencyRepo;
import com.github.zybercik00.repository.proces.MarginRepo;
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