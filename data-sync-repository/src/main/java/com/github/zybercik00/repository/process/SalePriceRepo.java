package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.SalePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePriceRepo extends JpaRepository<SalePrice, String> {
}
