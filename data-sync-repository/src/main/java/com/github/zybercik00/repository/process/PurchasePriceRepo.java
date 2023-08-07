package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.PurchasePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasePriceRepo extends JpaRepository<PurchasePrice, String> {
}
