package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Extraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractionRepo extends JpaRepository<Extraction, String> {
}
