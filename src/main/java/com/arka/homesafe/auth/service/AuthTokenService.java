package com.arka.homesafe.auth.service;

import com.arka.homesafe.auth.model.AuthTokens;
import com.arka.homesafe.auth.repo.AuthTokenRepo;
import com.arka.homesafe.user.model.User;
import com.arka.homesafe.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthTokenService {

    @Autowired
    AuthTokenRepo repo;

    public boolean validateToken(String token){
        AuthTokens tkn = repo.findAuthTokensByToken(token);

        if(token!=null){
            return tkn.isStatus() && tkn.getExpiryDate().before(new Date());
        }

        return false;
    }

    public AuthTokens saveToken(User user){
        AuthTokens token = new AuthTokens();
        token.setUser(user);
        token.setStatus(true);
        token.setExpiryDate(DateUtils.getDateAfter(90));
        token.setToken(UUID.randomUUID().toString());
        return repo.save(token);
    }
}
