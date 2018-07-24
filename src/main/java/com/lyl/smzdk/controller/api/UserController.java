package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAutoConfiguration
@RestController
public class UserController extends ApiBaseController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello world! OK";
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/saveUser")
    public void saveUser() {
        userRepository.save(new User("小白", "12345", "bai.x@qq.com"));
        userRepository.save(new User("小黑", "22222", "hei@qq.com"));
        userRepository.save(new User("小强", "33333", "qiang.x@qq.com"));
        userRepository.save(new User("校长", "444", "zhang.x@qq.com"));
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

