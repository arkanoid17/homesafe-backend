package com.arka.homesafe.auth.model.auth;

import com.arka.homesafe.auth.model.user.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse  {
    private User user;
    private String token;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
