package springmvc.controller;

import springmvc.controller.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<Employee>();

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable int id, Model model) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employeeDetails";
        } else {
            return "employeeNotFound";
        }
    }

    @PostMapping("/")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employees.add(employee);
        return "redirect:/employees/";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            return "redirect:/employees/";
        } else {
            return "employeeNotFound";
        }
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute("employee") Employee updatedEmployee) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setAddress(updatedEmployee.getAddress());
            // Update other employee details as needed
            return "redirect:/employees/";
        } else {
            return "employeeNotFound";
        }
    }

    private Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
}
