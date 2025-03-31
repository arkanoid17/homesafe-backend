package com.arka.homesafe.auth.repo;

import com.arka.homesafe.auth.model.AuthTokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepo extends JpaRepository<AuthTokens,Long> {
    public AuthTokens findAuthTokensByToken(String token);
}
