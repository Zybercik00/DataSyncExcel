package com.github.zybercik00.repository.proces;

import com.github.zybercik00.entity.process.Extraction;
import com.github.zybercik00.entity.process.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ExtractionRepo extends JpaRepository<Extraction, String> {
    Optional<Extraction> findByMaterialLotAndPreparedOn(String materialLot, Date preparedOn);
}
