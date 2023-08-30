package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.Extraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExtractionRepo extends JpaRepository<Extraction, String> {

    @Override
    List<Extraction> findAllById(Iterable<String> strings);

    Optional<Extraction> findByMaterialLotAndPreparedOn(String materialLot, Date preparedOn);
}
