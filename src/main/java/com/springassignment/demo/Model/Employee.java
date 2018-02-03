package com.springassignment.demo.Model;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
=======
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {

    @NotNull
>>>>>>> a3948912d8a7bf1a1aa627b5ba78ba0062b1d4e8
    private String name;

    @NotNull
    @Size(min=1000)
    private float salary;

    public Employee() {

    }

    public Employee(Long id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
