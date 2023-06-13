package com.abc.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity()
@Table(name = "employee_Info")
public class Employee {

    @Id
    @Column(name = "employee_Id")
    private int employeeId;

    @Column(name = "first_name", length = 40)
    private String firstname;

    @Column(name = "last_name", length = 40)
    private String lastname;

    @Column(name = "salary")
    private  double salary;

    @Column(name = "experience")
    private double experience;

    @Column(name = "department")
    private String department;

    @Column(name = "manager")
    private String reportingmanager;

}
