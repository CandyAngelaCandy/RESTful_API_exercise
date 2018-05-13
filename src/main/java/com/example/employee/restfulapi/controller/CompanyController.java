package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getSpecificCompany(@PathVariable("id") Long id) {
       return companyRepository.findOne(id);
    }

    @GetMapping("/{id}/employees")
    public Set<Employee> getEmployeeListInSpecificCompany(@PathVariable("id") Long id) {
       return companyRepository.findOne(id).getAllEmployees();
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Company> getCompaniesByPage(@PathVariable("page") int page,
                                            @PathVariable("pageSize") int pageSize) {
        Pageable pageable = new PageRequest(page-1,pageSize);
        return companyRepository.findAll(pageable).getContent();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable("id") Long id,
                                 @RequestBody Company company) {
        return companyRepository.exists(id)?companyRepository.save(company):null;
    }

    @DeleteMapping("/{id}")
    public void deleteSpecificCompanyAndALLEmployees(@PathVariable("id") Long id) {
        companyRepository.delete(id);
    }
}
