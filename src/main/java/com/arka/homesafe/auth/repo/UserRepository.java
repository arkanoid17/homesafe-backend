package com.arka.homesafe.auth.repo;

import com.arka.homesafe.auth.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String username);
}
