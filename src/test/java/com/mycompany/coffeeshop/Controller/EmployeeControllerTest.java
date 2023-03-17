package com.mycompany.coffeeshop.Controller;

import com.mycompany.coffeeshop.Exception.ResourceNotFoundException;
import com.mycompany.coffeeshop.Model.Employee;
import com.mycompany.coffeeshop.Service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeByIdSuccess() {
        Long id = 1L;
        Employee employee = new Employee(id, "John", null);
        when(employeeService.getEmployeeById(id)).thenReturn(employee);

        ResponseEntity<Object> responseEntity = employeeController.getEmployeeById(id);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(employee, responseEntity.getBody());
    }

    @Test
    public void testGetEmployee_ReturnsListOfEmployees() {
        // Arrange
        List<Employee> employees = new ArrayList<>();
        Employee emp1 = new Employee(1L, "Emp1", null);
        Employee emp2 = new Employee(2L, "Emp2", null);
        employees.add(emp1);
        employees.add(emp2);
        when(employeeService.getAllEmployee()).thenReturn(employees);

        // Act
        ResponseEntity<Object> responseEntity = employeeController.getEmployee();

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(employees);
    }

    @Test
    public void testGetEmployeeById_ReturnsEmployee_WhenFound() {
        // Arrange
        Employee emp = new Employee(1L, "Emp1", null);
        when(employeeService.getEmployeeById(emp.getEmployeeById())).thenReturn(emp);

        // Act
        ResponseEntity<Object> responseEntity = employeeController.getEmployeeById(emp.getEmployeeById());

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(emp);
    }

    @Test
    public void testGetEmployeeById_ReturnsNotFound_WhenEmployeeNotFound() {
        // Arrange
        Long id = 1L;
        when(employeeService.getEmployeeById(id)).thenThrow(new ResourceNotFoundException("Employee not found.","id",id));

        // Act
        ResponseEntity<Object> responseEntity = employeeController.getEmployeeById(id);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo("Employee not found. not found with id :"+" '"+id+"'");
    }

    @Test
    public void testCreateEmployee_ReturnsNewEmployee_WhenCreated() {
        // Arrange
        Employee emp = new Employee(1L, "Emp1", null);
        when(employeeService.createEmployee(emp)).thenReturn(emp);

        // Act
        ResponseEntity<Object> responseEntity = employeeController.createEmployee(emp);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(emp);
    }

    @Test
    public void testUpdateEmployee_ReturnsUpdatedEmployee_WhenUpdated() {
        // Arrange
        Long id = 1L;
        Employee emp = new Employee(id, "Emp1", null);
        when(employeeService.updateEmployee(id, emp)).thenReturn(emp);

        // Act
        ResponseEntity<Object> responseEntity = employeeController.updateEmployee(id, emp);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(emp);
    }

    @Test
    public void testUpdateEmployee_ReturnsNotFound_WhenEmployeeNotFound() {
        // Arrange
        Long id = 1L;
        Employee emp = new Employee(id, "Emp1", null);
        when(employeeService.updateEmployee(id, emp)).thenThrow(new ResourceNotFoundException("Employee not found.", "", ""));

        // Act
        ResponseEntity<Object> responseEntity = employeeController.updateEmployee(id, emp);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo("Employee not found. not found with  : ''");
    }

}
