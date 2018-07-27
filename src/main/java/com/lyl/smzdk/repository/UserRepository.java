package com.lyl.smzdk.repository;

import com.lyl.smzdk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNumberOrPhone(String number, String phone);
    int countByNumber(String number);
}
