package com.arka.homesafe.auth.controller;


import com.arka.homesafe.auth.model.auth.AuthRequest;
import com.arka.homesafe.auth.model.auth.AuthResponse;
import com.arka.homesafe.auth.model.user.User;
import com.arka.homesafe.auth.service.JWTService;
import com.arka.homesafe.auth.service.UserService;
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


@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){


        try{
//            request.setPassword(encoder.encode(request.getPassword()));
            Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            if (authentication.isAuthenticated()){

                AuthResponse response = new AuthResponse();
                response.setUser(service.getUserByEmail(request.getEmail()));
                response.setMessage(StringUtils.SUCCESS);
                response.setToken(jwtService.generateToken(request.getEmail()));

                return new ResponseEntity<>(response,HttpStatus.OK);

            }else{
                return new ResponseEntity<>(StringUtils.BAD_CREDENTIALS,HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(StringUtils.BAD_CREDENTIALS,HttpStatus.UNAUTHORIZED);
        }


    }

}
