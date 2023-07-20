package com.github.zybercik00.repository.proces;

import com.github.zybercik00.domain.proces.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
    Optional<Employee> findByName(String name);
}
