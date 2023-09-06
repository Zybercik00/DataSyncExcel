package com.github.zybercik00;

import com.github.zybercik00.domain.process.Currency;
import com.github.zybercik00.repository.process.CurrencyRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepo repository;

    public Currency getByCode(String code) {
        return repository.findByCode(code)
                .orElseGet(() -> repository.save(new Currency(code)));
    }
}