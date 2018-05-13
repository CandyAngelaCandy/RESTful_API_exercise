package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getSpecificEmployee(@PathVariable("id") Long id){
        return employeeRepository.findOne(id);
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Employee> getPageEmployeeList(@PathVariable("page") int page,
                                              @PathVariable("pageSize") int pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize);
        return employeeRepository.findAll(pageable).getContent();
    }

    @GetMapping("/male")
    public List<Employee> getSpecificGenderEmployee() {
        return employeeRepository.findAllByGender("male");
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
         return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id,@RequestBody Employee newEmployee){

       return  employeeRepository.exists(id)?employeeRepository.save(newEmployee):null;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
