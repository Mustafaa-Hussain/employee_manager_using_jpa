package com.example.employee_manager_using_jpa.repositories;

import com.example.employee_manager_using_jpa.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainsIgnoreCase(String name);

    List<Employee> findByDepartment_Name(String name);

}
