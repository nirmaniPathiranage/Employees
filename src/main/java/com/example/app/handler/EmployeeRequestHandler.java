package com.example.app.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.app.dao.EmployeeDao;
import com.example.app.dao.EmployeeDaoImpl;
import com.example.app.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Entry point for Employee API Gateway
 */
public class EmployeeRequestHandler {

    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        if (this.employeeDao == null) {
            this.employeeDao = new EmployeeDaoImpl();
        }
        return this.employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public APIGatewayProxyResponseEvent testLambda(APIGatewayProxyRequestEvent request, Context context) {

        Employee employee = new Employee();
        employee.setEmployeeId("001");
        employee.setEmployeeName("John");
        employee.setEmployeeAge(30);
        employee.setEmployeeAddress("Toronto, Canada");
        employee.setEmployeeContact("11234567890");
        employee.setDepartmentCode("D001");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(employee);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        }
    }

    public APIGatewayProxyResponseEvent listEmployees(APIGatewayProxyRequestEvent request, Context context) {
        List<Employee> employees = this.getEmployeeDao().listEmployees();

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(employees);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        }

    }

    public APIGatewayProxyResponseEvent createEmployee(APIGatewayProxyRequestEvent request, Context context) {

        String body = request.getBody();

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;

        try {
            employee = mapper.readValue(body, Employee.class);

            this.getEmployeeDao().saveEmployee(employee);
            return new APIGatewayProxyResponseEvent().withStatusCode(201);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        }
    }

    public APIGatewayProxyResponseEvent getEmployee(APIGatewayProxyRequestEvent request, Context context) {
        String employeeId = request.getPathParameters().get("id");
        Employee employee = this.getEmployeeDao().getEmployee(employeeId);

        if (employee == null) {
            return new APIGatewayProxyResponseEvent().withStatusCode(404);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(employee);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        }
    }

    public APIGatewayProxyResponseEvent updateEmployee(APIGatewayProxyRequestEvent request, Context context) {
        String body = request.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Employee employee;

        try {
            employee = mapper.readValue(body, Employee.class);
            this.getEmployeeDao().saveEmployee(employee);
            return new APIGatewayProxyResponseEvent().withStatusCode(200);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new APIGatewayProxyResponseEvent().withStatusCode(500);
        }
    }

    public APIGatewayProxyResponseEvent deleteEmployee(APIGatewayProxyRequestEvent request, Context context) {
        String employeeId = request.getPathParameters().get("id");
        this.getEmployeeDao().deleteEmployee(employeeId);

        return new APIGatewayProxyResponseEvent().withStatusCode(200);
    }

}
