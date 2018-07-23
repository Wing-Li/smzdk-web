package com.lyl.smzdk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
    User findByUserNameOrEmail(String name, String email);
}
