package com.github.zybercik00.repository.process;

import com.github.zybercik00.domain.process.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepo extends JpaRepository<Waste, String> {
}
