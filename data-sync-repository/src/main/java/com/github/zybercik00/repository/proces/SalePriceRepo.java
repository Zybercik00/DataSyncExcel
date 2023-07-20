package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.SalePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePriceRepo extends JpaRepository<SalePrice, String> {
}
