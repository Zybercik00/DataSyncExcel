package com.github.zybercik00.entity.process;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {

    @Id
    @GeneratedValue

    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "EMPLOYEE_NAME")
    private String name;


    @Column(name = "POSITIN")
    private String position;


    @Column(name = "ACCESS_LEVEL")
    private String accessLevel;
}
