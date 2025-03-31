package com.arka.homesafe.company.aspect;

import com.arka.homesafe.auth.config.JwtFilter;
import com.arka.homesafe.auth.service.JWTService;
import com.arka.homesafe.threadlocal.RequestContext;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class JwtAspect {

    @Autowired
    private JWTService jwtService;

    // Apply this aspect to all methods in CompanyController
    @Pointcut("within(com.arka.homesafe.company.controller.CompanyController)")
    public void companyControllerMethods() {}

    @Before("companyControllerMethods()")
    public void extractUserIdFromToken() {
        // Get the current HTTP request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;

        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer "
            Claims claims = jwtService.extractAllClaims(token);

            // Extract userId from claims
            long userId = claims.get("userId", Long.class);

            // Store in ThreadLocal for this request
            RequestContext.setUserId(userId);
        } else {
            System.out.println("No Authorization token found in request");
        }
    }


    @After("companyControllerMethods()")
    public void clearUserId() {
        RequestContext.clear();
    }

}
