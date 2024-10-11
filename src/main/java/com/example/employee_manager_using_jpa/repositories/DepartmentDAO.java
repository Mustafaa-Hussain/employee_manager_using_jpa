package com.example.employee_manager_using_jpa.repositories;

import com.example.employee_manager_using_jpa.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department, Long> {
}
