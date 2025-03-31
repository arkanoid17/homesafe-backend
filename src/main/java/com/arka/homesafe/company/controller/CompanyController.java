package com.arka.homesafe.company.controller;

import com.arka.homesafe.company.model.Company;
import com.arka.homesafe.company.service.CompanyService;
import com.arka.homesafe.threadlocal.RequestContext;
import com.arka.homesafe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api")
public class CompanyController {

    @Autowired
    CompanyService service;

    @Autowired
    UserService userService;

    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader Map<String,String> headers){
        try{
            long userId = RequestContext.getUserId();
            company.setUser(userService.getUser(userId));
            return new ResponseEntity<>(service.addCompany(company), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> addCompany(@PathVariable long id, @RequestHeader Map<String,String> headers){
        try{
            return new ResponseEntity<>(service.getCompany(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
