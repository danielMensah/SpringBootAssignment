package com.springassignment.demo.Controller;

import com.springassignment.demo.Database.EmployeeRepository;
import com.springassignment.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class SalaryController{
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/salaryCalculator")
    public String salaryForm(Model model) {

        model.addAttribute("employeeList", employeeRepository.getAllEmployees());
        model.addAttribute("employee", new Employee());
        return "salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=add")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeRepository.addEmployeeToDatabase(employee);
        return "redirect:/salaryCalculator";
    }

    //Remove single employee
    @RequestMapping(value="/manageEmployee", method=RequestMethod.GET)
    public String removeEmployee(@RequestParam(name="employee")Long id){
        employeeRepository.removeEmployee(id);
        return "redirect:/salaryCalculator";
    }
    //Update single employee
    @RequestMapping(value="/manageEmployee",method=RequestMethod.POST, params="action=update")
    public String updateEmployee(@RequestParam(name="employee") Employee employee){
        System.out.println(employee.getId());
        employeeRepository.updateEmployee(employee.getId(),employee.getName(),employee.getSalary());
        return "redirect:/salaryCalculator";
    }
    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=removeAll")
    public String removeEmployees(@ModelAttribute("employee") Employee employee){
        employeeRepository.removeEmployees();
        return "redirect:/salaryCalculator";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=mean")
    public String calculateMeanSalary(Model model) {
        BigDecimal sum = new BigDecimal(0.0);
        List<Employee> employees = employeeRepository.getAllEmployees();

        for (Employee employee : employees) {
            sum = sum.add(employee.getSalary());
        }

        BigDecimal mean = sum.divide(BigDecimal.valueOf(employees.size()), BigDecimal.ROUND_HALF_UP);
        Employee employeeWithHighSalary = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        Employee employeeWithLowSalary = employees.stream().min(Comparator.comparing(Employee::getSalary)).get();

        model.addAttribute("result", mean);
        model.addAttribute("operation", "mean");
        model.addAttribute("hEmployee", employeeWithHighSalary);
        model.addAttribute("lEmployee", employeeWithLowSalary);

        return "result";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=sum")
    public String calculateSumSalary(Model model) {
        BigDecimal sum = new BigDecimal(0.0);
        List<Employee> employees = employeeRepository.getAllEmployees();

        for (Employee employee : employees) {
            sum = sum.add(employee.getSalary());
        }

        Employee employeeWithHighSalary = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        Employee employeeWithLowSalary = employees.stream().min(Comparator.comparing(Employee::getSalary)).get();

        model.addAttribute("result", sum);
        model.addAttribute("operation", "sum");
        model.addAttribute("hEmployee", employeeWithHighSalary);
        model.addAttribute("lEmployee", employeeWithLowSalary);

        return "result";
    }

    @RequestMapping(value="/manageEmployee", method=RequestMethod.POST, params="action=std")
    public String calculateStandardDeviation(Model model) {
        List<Employee> employees = employeeRepository.getAllEmployees();
        List<Double> salaryList = new ArrayList<>();
        double sum = 0;
        double sumSquare = 0;

        for (Employee employee : employees) {
            sum += employee.getSalary().doubleValue();
            salaryList.add(employee.getSalary().doubleValue());
        }

        double mean = sum / employees.size();

        for (double salary : salaryList) {
            double distance = Math.abs(mean - salary);
            double distanceSquare = distance * distance;
            sumSquare += distanceSquare;
        }

        double STD = Math.abs(sumSquare / employees.size());

        Employee employeeWithHighSalary = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        Employee employeeWithLowSalary = employees.stream().min(Comparator.comparing(Employee::getSalary)).get();

        model.addAttribute("result", STD);
        model.addAttribute("operation", "standard deviation");
        model.addAttribute("hEmployee", employeeWithHighSalary);
        model.addAttribute("lEmployee", employeeWithLowSalary);

        return "result";
    }

}
