package com.maharyadana.dashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maharyadana.dashboard.Service.EmployeeService;
import com.maharyadana.dashboard.model.Employee;

@Controller
public class ClientController {
    @Autowired
    private EmployeeService employeeService;

    // OLD
    // @GetMapping("/")
    // public String client(Model model) {
    // model.addAttribute("listEmployees", employeeService.getAllEmployee());
    // return "client";
    // }

    // OLD
    // display list of employees
    // @GetMapping("/")
    // public String viewHomePage(Model model) {
    // return findPaginated(1, model);
    // }

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind fro data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    // OLD
    // @GetMapping("/page/{pageNo}")
    // public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model
    // model) {
    // int pageSize = 5;

    // Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
    // List<Employee> listEmployees = page.getContent();

    // model.addAttribute("currentPage", pageNo);
    // model.addAttribute("totalPages", page.getTotalPages());
    // model.addAttribute("totalItems", page.getTotalElements());
    // model.addAttribute("listEmployees", listEmployees);
    // return "client";
    // }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "client";
    }
}
