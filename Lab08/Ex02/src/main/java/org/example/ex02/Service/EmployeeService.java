package org.example.ex02.Service;


import org.example.ex02.Model.Employee;
import org.example.ex02.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public void createEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepo.findById(id).get();
    }
}
