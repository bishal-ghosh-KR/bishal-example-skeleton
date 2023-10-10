package com.kroger.bishalexampleskeleton.service.controller;

import com.kroger.bishalexampleskeleton.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void addEmployee() {
        try {
            Employee emp1 = createEmployee(1L, "John", "NY");
            webTestClient.post()
                    .uri("/employee/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(emp1), Employee.class)
                    .exchange()
                    .expectStatus()
                    .isCreated()
                    .expectBody();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void getEmployee() {
        try {
            webTestClient.get()
                    .uri("/employee/get/1")
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectHeader()
                    .contentType(MediaType.APPLICATION_JSON)
                    .expectBody(Employee.class);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void getEmployeeNotFound() {
        try {
            webTestClient.get()
                    .uri("/employee/get/2")
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void updateEmployee() {
        try {
            Employee emp1 = createEmployee(1L, "Adam", "NY");
            webTestClient.put()
                    .uri("/employee/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(emp1), Employee.class)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectHeader()
                    .contentType(MediaType.APPLICATION_JSON)
                    .expectBody(Employee.class);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(5)
    void deleteEmployee() {
        try {
            webTestClient.delete()
                    .uri("/employee/delete/1")
                    .exchange()
                    .expectStatus()
                    .isOk();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(6)
    void allEmployees() {
        try {
            webTestClient.get()
                    .uri("/employee/all")
                    .exchange()
                    .expectStatus()
                    .isOk();
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
