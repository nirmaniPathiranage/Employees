package com.example.app.dao;

import java.util.List;

import com.example.app.model.Employee;

public interface EmployeeDao {
    public List<Employee> listEmployees();

    public Employee getEmployee(String id);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(String id);

}
