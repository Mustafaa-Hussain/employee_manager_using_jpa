package com.example.employee_manager_using_jpa.errors;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(){
        super("Employee with the given id is not found.");
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
