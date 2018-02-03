package com.springassignment.demo.Model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {

    @NotNull
    private String name;

    @NotNull
    @Size(min=1000)
    private float salary;

    public Employee() {

    }

    public Employee(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
