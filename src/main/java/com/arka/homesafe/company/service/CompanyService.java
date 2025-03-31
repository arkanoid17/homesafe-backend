package com.arka.homesafe.company.service;

import com.arka.homesafe.company.model.Company;
import com.arka.homesafe.company.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepo;

    public Company addCompany(Company company){
        return companyRepo.save(company);
    }

    public List<Company> getCompanies(){
        return companyRepo.findAll();
    }

    public Object getCompany(long id) {
        return companyRepo.findById(id);
    }
}
