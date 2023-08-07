package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.Extraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractionRepo extends JpaRepository<Extraction, String> {
}
