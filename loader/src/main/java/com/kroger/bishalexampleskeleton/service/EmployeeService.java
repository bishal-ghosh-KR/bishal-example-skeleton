package com.kroger.bishalexampleskeleton.service;

import com.kroger.bishalexampleskeleton.entity.Employee;
import com.kroger.bishalexampleskeleton.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private EmployeeRepository empRepository;

    public EmployeeService(EmployeeRepository empRepository) {
        this.empRepository = empRepository;
    }

    public Mono<Employee> getEmployee(Long id) {
        return empRepository.findById(id);
    }

    public Mono<Employee> createEmployee(Employee employee) {
        return empRepository.save(employee);
    }

    public Mono<Employee> updateEmployee(Employee employee) {
        return empRepository.findById(employee.getId())
                .flatMap(dbEmp -> {
                    dbEmp.setCity(employee.getCity());
                    dbEmp.setName(employee.getName());
                    return empRepository.save(dbEmp.setAsNew(false));
                });
    }

    public Mono<String> deleteEmployee(Long id){
        return empRepository.findById(id)
                .flatMap(empRepository::delete)
                .then(Mono.just(String.format("Employee with ID: %d is deleted success fully", id)));
    }

    public Flux<Employee> getAllUsers() {
        return empRepository.findAll();
    }
}
