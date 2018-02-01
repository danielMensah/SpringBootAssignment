package com.springassignment.demo.Controller;

import com.springassignment.demo.Model.EmployeeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SalaryController {

    @GetMapping("/calculatesalary")
    public String salaryForm(Model model) {
        model.addAttribute("employee", new EmployeeModel());
        return "calculatesalary";
    }
    @PostMapping("/calculatesalary")
    public String salarySubmit(@ModelAttribute EmployeeModel employee){
        return "result";
    }
    private float calculateMeanSalary(List<Float> listOfSalaries) {
        float mean = 0.0f;

        return mean;
    }
}
