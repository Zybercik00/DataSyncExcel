package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Margin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarginRepo extends JpaRepository<Margin, String> {
    Optional<Margin> findByName(String name);
}
