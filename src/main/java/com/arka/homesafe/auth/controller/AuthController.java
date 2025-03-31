package com.arka.homesafe.auth.controller;


import com.arka.homesafe.auth.model.AuthRequest;
import com.arka.homesafe.auth.model.AuthResponse;
import com.arka.homesafe.auth.model.AuthTokens;
import com.arka.homesafe.auth.service.AuthService;
import com.arka.homesafe.auth.service.AuthTokenService;
import com.arka.homesafe.auth.service.JWTService;
import com.arka.homesafe.user.service.UserService;
import com.arka.homesafe.utils.DateUtils;
import com.arka.homesafe.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    UserService service;

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager manager;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            if (authentication.isAuthenticated()){
                AuthResponse resp = authService.getUserByEmail(request.getEmail());
                return new ResponseEntity<>(resp,HttpStatus.OK);

            }else{
                return new ResponseEntity<>(StringUtils.BAD_CREDENTIALS,HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(StringUtils.BAD_CREDENTIALS,HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        try{
            AuthResponse resp = authService.registerUser(request);

            if (resp!=null){
                return new ResponseEntity<>(resp,HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Error registering user!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
