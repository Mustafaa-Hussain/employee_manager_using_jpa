package com.example.employee_manager_using_jpa.controllers;

import com.example.employee_manager_using_jpa.entities.Department;
import com.example.employee_manager_using_jpa.errors.DepartmentNotFoundException;
import com.example.employee_manager_using_jpa.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(departmentService.getById(id));
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(departmentService.getAll());
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addNewDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.insert(department));
    }


    @PutMapping("/{id}")
    public ResponseEntity editDepartment(@RequestBody Department newDepartment, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(departmentService.editById(newDepartment, id));
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
