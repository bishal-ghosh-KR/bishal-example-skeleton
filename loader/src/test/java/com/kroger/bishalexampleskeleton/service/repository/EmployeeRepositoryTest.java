package com.kroger.bishalexampleskeleton.service.repository;

import com.kroger.bishalexampleskeleton.entity.Employee;
import com.kroger.bishalexampleskeleton.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(value = 1)
    void connection() {
        Assertions.assertNotNull(employeeRepository);
    }

    @Test
    @Order(value = 2)
    void addEmployee() {
        try {
            Employee emp1 = createEmployee(1L, "John", "NY");
            Employee emp2 = createEmployee(2L, "Bryan", "OH");
            employeeRepository.save(emp1).block();
            employeeRepository.save(emp2).block();
            List<Employee> employees = employeeRepository.findAll().collectList().block();
            Assertions.assertEquals(2, employees.size());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(value = 3)
    void getEmployee() {
        try {
            Employee employee = employeeRepository.findById(1L).block();
            Assertions.assertNotNull(employee);
            Assertions.assertEquals("John", employee.getName());
            Assertions.assertEquals("NY", employee.getCity());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(value = 4)
    void updateEmployee() {
        try {
            Employee emp1 = createEmployee(1L, "Adam", "NY");
            employeeRepository.save(emp1.setAsNew(false)).block();
            Employee employee = employeeRepository.findById(1L).block();
            Assertions.assertNotNull(employee);
            Assertions.assertEquals("Adam", employee.getName());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(value = 5)
    void deleteEmployee() {
        try {
            employeeRepository.deleteById(1L).block();
            Employee employee = employeeRepository.findById(1L).block();
            Assertions.assertNull(employee);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    private Employee createEmployee(Long id, String name, String city) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setCity(city);
        return employee;
    }
}
