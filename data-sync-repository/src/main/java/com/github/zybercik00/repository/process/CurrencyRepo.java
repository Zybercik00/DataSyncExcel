package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepo extends JpaRepository<Currency, String> {
    Optional<Currency> findByCode(String code);
}
