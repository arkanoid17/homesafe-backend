package com.arka.homesafe.auth.model;

import com.arka.homesafe.user.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse  {

    private User user;
    private String accessToken;
    private String refreshToken;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
