package com.kroger.bishalexampleskeleton.controller;

import com.kroger.bishalexampleskeleton.entity.Employee;
import com.kroger.bishalexampleskeleton.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService postgresService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Employee>> saveEmployee(@RequestBody Employee employee) {
        return postgresService.createEmployee(employee)
                .map(emp -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(emp)
                );
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Employee>> getEmployee(@PathVariable Long id) {
        return postgresService.getEmployee(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Employee>> updateEmployee(@RequestBody Employee employee) {
        return postgresService.updateEmployee(employee)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> deleteEmployeeById(@PathVariable Long id) {
        return postgresService.deleteEmployee(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<Employee>> getAllEmployees() {
        /*
            Here, instead of converting it into Mono<List<Employee>>,
            I have returned it as ResponseEntity<Flux<T>>(here, T is Employee)
            because we would be using collectList() and since we are querying
            database which might contain huge data.
        */
        return ResponseEntity.ok(postgresService.getAllUsers());
    }
}
