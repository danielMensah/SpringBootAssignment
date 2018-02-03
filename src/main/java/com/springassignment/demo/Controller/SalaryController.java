package com.springassignment.demo.Controller;

import com.springassignment.demo.Model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class SalaryController {
    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping("/salaryCalculator")
    public String salaryForm(Model model) {

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("employee", new Employee());
        return "salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=add")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeList.add(employee);
        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=remove")
    public String removeEmployee(@ModelAttribute("employee") Employee employee){
        employeeList.remove(employee);
        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=mean")
    public String calculateMeanSalary(Model model) {
        float sum = 0.0f;

        for (Employee employee : employeeList) {
            sum = sum + employee.getSalary();
        }

        float mean = sum / employeeList.size();
        Employee employeeWithHighIncome = employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();

        model.addAttribute("mean", mean);
        model.addAttribute("employee", employeeWithHighIncome);

        return "result";
    }
}
