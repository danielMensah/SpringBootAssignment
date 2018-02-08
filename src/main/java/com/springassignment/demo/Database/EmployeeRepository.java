package com.springassignment.demo.Database;
/**
 * @author Sergio Gayon Benavides
 * @With the help of Aaron Obeng and Daniel Mensah
 */
import com.springassignment.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee findById(long id){
        return jdbcTemplate.queryForObject("select * from EMPLOYEE where id=?", new Object[]{
                id
        },new BeanPropertyRowMapper<Employee>(Employee.class));
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM EMPLOYEE";

        List<Employee> employees = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        rows.forEach((Map<String, Object> row) -> {
            Employee employee = new Employee();

            employee.setId((Long)(row.get("id")));
            employee.setName((String)(row.get("name")));
            employee.setSalary((BigDecimal)(row.get("salary")));

            employees.add(employee);

        });

        return employees;
    }

    public void addEmployeeToDatabase(Employee employee){
        String sql = "INSERT into EMPLOYEE(name,salary) " + "VALUES(?,?) ";
        Object[] params = {employee.getName(),employee.getSalary()};

        jdbcTemplate.update(sql, params);
    }

    public void removeEmployees() {
        List<Employee> employees = this.getAllEmployees();

        for (Employee employee : employees) {
            String sql = "DELETE from EMPLOYEE WHERE id IN ("+employee.getId()+")";

            jdbcTemplate.update(sql);
        }
    }

    public void updateEmployee(Employee employee){
        String sql = "UPDATE EMPLOYEE SET NAME =? , SALARY=?  WHERE id  = ?";
//        String sql = "UPDATE EMPLOYEE(name, salary) SET VALUES(?,?) WHERE id IN ("+employee.getId()+")";
//        String sql = "UPDATE EMPLOYEE ";
        Object[] params = {employee.getName(), employee.getSalary(),employee.getId()};
        jdbcTemplate.update(sql,params);
    }

    public void removeEmployee(Long id) {
        String sql = "DELETE from EMPLOYEE WHERE id = ?";
        Object[] params = {id};

        jdbcTemplate.update(sql, params);

    }
}
