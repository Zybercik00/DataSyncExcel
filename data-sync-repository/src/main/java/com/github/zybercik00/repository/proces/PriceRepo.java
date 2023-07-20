package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<Price, String> {
}
