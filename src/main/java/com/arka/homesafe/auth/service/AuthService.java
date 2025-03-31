package com.arka.homesafe.auth.service;

import com.arka.homesafe.auth.controller.AuthController;
import com.arka.homesafe.auth.model.AuthRequest;
import com.arka.homesafe.auth.model.AuthResponse;
import com.arka.homesafe.auth.model.AuthTokens;
import com.arka.homesafe.auth.repo.UserRepository;
import com.arka.homesafe.user.model.User;
import com.arka.homesafe.user.model.UserRole;
import com.arka.homesafe.user.service.UserService;
import com.arka.homesafe.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthTokenService tokenService;

    @Autowired
    UserService userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public AuthResponse getUserByEmail(String email) {

        User user = userRepo.findUserByEmail(email);
        AuthResponse response = new AuthResponse();
        response.setUser(user);
        response.setMessage(StringUtils.SUCCESS);
        response.setAccessToken(jwtService.generateToken(email,user.getId()));
        response.setRefreshToken(tokenService.saveToken(response.getUser()).getToken());

        return response;
    }

    public AuthResponse registerUser(AuthRequest request) throws Exception{

        User user = new User();
        user.setFullName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUserRole(request.getRole().equalsIgnoreCase("CUSTOMER")? UserRole.CUSTOMER:UserRole.SERVICE_PROVIDER);
        user = userService.addUser(user);

        AuthResponse response = new AuthResponse();
        response.setUser(user);
        response.setMessage(StringUtils.SUCCESS);
        response.setAccessToken(jwtService.generateToken(user.getEmail(),user.getId()));
        response.setRefreshToken(tokenService.saveToken(response.getUser()).getToken());

        return response;


    }
}
