package com.example.employee_manager_using_jpa.errors;

public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException(){
        super("Department with the given id is not found.");
    }

    public DepartmentNotFoundException(String message){
        super(message);
    }
}
