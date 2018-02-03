package com.springassignment.demo.Controller;

import com.springassignment.demo.Database.EmployeeRespository;
import com.springassignment.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class SalaryController{
    @Autowired
    EmployeeRespository employeeRespository;

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping("/salaryCalculator")
    public String salaryForm(Model model) {

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("employee", new Employee());
        return "salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=add")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeRespository.addEmployeeToDatabase(employee);
        employeeList.add(employee);
        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.GET)
    public String removeEmployee(@RequestParam(name="employee")String name){

        for (Employee e : employeeList) {
            if (e.getName().equals(name)) {
                employeeList.remove(e);
                break;
            }
        }

        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=removeAll")
    public String removeEmployees(@ModelAttribute("employee") Employee employee){
        List<Employee> employees = employeeList;
        employeeList.removeAll(employees);
        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=mean")
    public String calculateMeanSalary(Model model) {
        float sum = 0.0f;

        for (Employee employee : employeeList) {
            sum = sum + employee.getSalary();
        }

        float mean = sum / employeeList.size();
        Employee employeeWithHighSalary = employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();

        model.addAttribute("mean", mean);
        model.addAttribute("employee", employeeWithHighSalary);

        return "result";
    }
}
