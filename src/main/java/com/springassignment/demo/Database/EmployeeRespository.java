package com.springassignment.demo.Database;

import com.springassignment.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRespository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee findById(int id){
        return jdbcTemplate.queryForObject("select * from EMPLOYEE where id=?", new Object[]{
                id
        },new BeanPropertyRowMapper<Employee>(Employee.class));
    }
    public int addEmployeeToDatabase(Employee employee){
        return jdbcTemplate.update("insert into EMPLOYEE(name,salary) " + "values(?,?)", new Object[]{
                employee.getName(),employee.getSalary()
        });
    }
}
