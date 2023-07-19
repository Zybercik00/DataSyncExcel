package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepo extends JpaRepository<Material, String> {

    Optional<Material> findByLot(String lot);
}
