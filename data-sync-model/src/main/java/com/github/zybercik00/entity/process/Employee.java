package com.github.zybercik00.entity.process;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EMPLOYEE",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_EMPLOYEE_NME",
                columnNames = "EMPLOYEE_NAME"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Employee {

    @Id
    @GeneratedValue

    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "EMPLOYEE_NAME")
    private String name;


    @Column(name = "EMP_POSITION")
    private String position;


    @Column(name = "ACCESS_LEVEL")
    private String accessLevel;
}
