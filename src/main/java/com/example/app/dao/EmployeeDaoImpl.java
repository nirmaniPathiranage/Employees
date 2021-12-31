package com.example.app.dao;

import java.util.List;
import java.util.UUID;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.app.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public EmployeeDaoImpl() {
        this.client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        this.mapper = new DynamoDBMapper(this.client);
    }

    @Override
    public List<Employee> listEmployees() {
        return this.mapper.scan(Employee.class, new DynamoDBScanExpression());
    }

    @Override
    public Employee getEmployee(String id) {
        return this.mapper.load(Employee.class, id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        if (employee.getEmployeeId() == null) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        this.mapper.save(employee);

    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = this.getEmployee(id);
        if (employee != null) {
            this.mapper.delete(employee);
        }

    }

}
