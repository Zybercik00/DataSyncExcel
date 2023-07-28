package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepo extends JpaRepository<Currency, String> {
    Optional<Currency> findByCode(String code);
}
