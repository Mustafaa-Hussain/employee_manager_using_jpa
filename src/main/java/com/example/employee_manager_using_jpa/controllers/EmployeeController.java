package com.example.employee_manager_using_jpa.controllers;

import com.example.employee_manager_using_jpa.entities.Employee;
import com.example.employee_manager_using_jpa.errors.EmployeeNotFoundException;
import com.example.employee_manager_using_jpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.getById(id));
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/filter", params = "name")
    public ResponseEntity getByName(@RequestParam(name = "name") String employeeName){
        return ResponseEntity.ok(employeeService.getByName(employeeName));
    }

    @GetMapping(value = "/filter", params = "department")
    public ResponseEntity getByDepartment(@RequestParam(name = "department") String departmentName){
        return ResponseEntity.ok(employeeService.getByDepartment(departmentName));
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(employeeService.getAll());
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.insert(employee));
    }


    @PutMapping("/{id}")
    public ResponseEntity editEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.editById(newEmployee, id));
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
