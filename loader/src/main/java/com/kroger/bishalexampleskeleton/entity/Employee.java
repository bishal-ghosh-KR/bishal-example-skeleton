package com.kroger.bishalexampleskeleton.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public class Employee implements Persistable<Long> {

    @Id
    private Long id;
    private String name;
    private String city;
    @Transient
    private boolean newEmployee = true;

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.newEmployee;
    }

    public Employee setAsNew(boolean flag) {
        this.newEmployee = flag;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
