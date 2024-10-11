package com.example.employee_manager_using_jpa.services;

import com.example.employee_manager_using_jpa.entities.Department;
import com.example.employee_manager_using_jpa.errors.DepartmentNotFoundException;
import com.example.employee_manager_using_jpa.repositories.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDAO departmentDAO;

    public Department getById(Long id) throws DepartmentNotFoundException {
        return departmentDAO.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
    }

    public Iterable<Department> getAll() throws DepartmentNotFoundException {
        return departmentDAO.findAll();
    }

    public Department insert(Department department) {
        return departmentDAO.save(department);
    }

    public Department editById(Department newDepartment, Long id) throws DepartmentNotFoundException {
        Department oldDepartment = departmentDAO.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));

        oldDepartment.setName(newDepartment.getName() != null ? newDepartment.getName() : oldDepartment.getName());

        return departmentDAO.save(oldDepartment);
    }

    public void delete(Long id) throws DepartmentNotFoundException {
        departmentDAO.deleteById(id);
    }
}
