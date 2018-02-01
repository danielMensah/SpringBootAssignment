package com.springassignment.demo.Controller;

import com.springassignment.demo.Model.EmployeeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SalaryController {

    @RequestMapping("/calculateSalary")
    public String showForm(Model model) {
        model.addAttribute("employee", new EmployeeModel());
        return "employee";
    }

    private float calculateMeanSalary(List<Float> listOfSalaries) {
        float mean = 0.0f;

        return mean;
    }
}
