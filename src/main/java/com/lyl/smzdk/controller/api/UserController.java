package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@EnableAutoConfiguration
@RestController
public class UserController extends ApiBaseController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/saveUser")
    public void createUser(String number, String name, String icon, String signature, int sex, String birth, String phone, String email, String province, String city, int vip_grade, long integral) {
        User user = new User();
        userRepository.save(user);
    }

    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @RequestMapping("/getUser")
    public User getUser(String name) {
        return userRepository.findByUserName(name);
    }
}

