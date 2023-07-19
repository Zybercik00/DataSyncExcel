package com.github.zybercik00;

import com.github.zybercik00.domain.proces.Employee;
import com.github.zybercik00.repository.proces.EmployeeRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo repository;

    public Employee getEmployee(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(Employee
                        .builder()
                        .name(name)
                        .build()));
    }
}
