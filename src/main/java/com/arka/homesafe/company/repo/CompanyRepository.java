package com.arka.homesafe.company.repo;

import com.arka.homesafe.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
