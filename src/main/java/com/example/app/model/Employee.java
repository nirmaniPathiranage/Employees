package com.example.app.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "employees")
public class Employee {
    private String employeeId;
    private String employeeName;
    private Integer employeeAge;
    private String employeeAddress;
    private String employeeContact;
    private String departmentCode;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Integer employeeAge, String employeeAddress,
                    String employeeContact, String departmentCode) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeAddress = employeeAddress;
        this.employeeContact = employeeContact;
        this.departmentCode = departmentCode;
    }

    @DynamoDBHashKey(attributeName = "employeeId")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @DynamoDBAttribute(attributeName = "employeeName")
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @DynamoDBAttribute(attributeName = "employeeAge")
    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    @DynamoDBAttribute(attributeName = "employeeAddress")
    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @DynamoDBAttribute(attributeName = "employeeContact")
    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    @DynamoDBAttribute(attributeName = "departmentCode")
    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeAge=" + employeeAge
                + ", employeeAddress=" + employeeAddress + ", employeeContact=" + employeeContact + ", departmentCode="
                + departmentCode + "]";
    }

}
