package com.lyl.smzdk.repository;

import com.lyl.smzdk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过 用户名 或 手机 查询一个用户
     */
    User findByNumberOrPhone(String number, String phone);

    /**
     * 查看 用户名 是否存在
     */
    int countByNumber(String number);

    /**
     * 查看 昵称 是否存在
     */
    int countByName(String name);
}
