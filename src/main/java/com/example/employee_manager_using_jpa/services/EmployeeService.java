package com.example.employee_manager_using_jpa.services;

import com.example.employee_manager_using_jpa.entities.Employee;
import com.example.employee_manager_using_jpa.errors.EmployeeNotFoundException;
import com.example.employee_manager_using_jpa.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    public Employee getById(Long id) throws EmployeeNotFoundException {
        return employeeDAO.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public List<Employee> getByName(String name){
        return employeeDAO.findByNameContainsIgnoreCase(name);
    }

    public List<Employee> getByDepartment(String name){
        return employeeDAO.findByDepartment_Name(name);
    }

    public Iterable<Employee> getAll() throws EmployeeNotFoundException {
        return employeeDAO.findAll();
    }

    public Employee insert(Employee employee) {
        return employeeDAO.save(employee);
    }

    public Employee editById(Employee newEmployee, Long id) throws EmployeeNotFoundException {
        Employee oldEmployee = employeeDAO.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        oldEmployee.setName(newEmployee.getName() != null ? newEmployee.getName() : oldEmployee.getName());
        oldEmployee.setSalary(newEmployee.getSalary() != null ? newEmployee.getSalary() : oldEmployee.getSalary());
        oldEmployee.setDepartment(newEmployee.getDepartment() != null ? newEmployee.getDepartment() : oldEmployee.getDepartment());

        return employeeDAO.save(oldEmployee);
    }

    public void delete(Long id) throws EmployeeNotFoundException {
        employeeDAO.deleteById(id);
    }
}
