package com.github.zybercik00.repository.proces;

import com.github.zybercik00.entity.process.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepo extends JpaRepository<Waste, String> {
}
