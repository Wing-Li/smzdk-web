package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 管理用户的逻辑
 */
@EnableAutoConfiguration
@RestController
public class UserController extends ApiBaseController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 创建用户
     *
     * @param number
     * @param name
     * @param icon
     * @param signature
     * @param sex
     * @param birth
     * @param phone
     * @param email
     * @param province
     * @param city
     */
    @RequestMapping("/createUser")
    public void createUser(String number, String name, String icon, String signature, Integer sex, String birth, String phone, String email, String province, String city) {
        User user = new User();

        user.setName(name);
        user.setSex(sex);
        Date timestamp = new Date(System.currentTimeMillis());
        user.setCreate_time(timestamp);
        user.setUpdate_time(timestamp);

        userRepository.save(user);
    }

    /**
     * 获取所有用户
     */
    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/getUser")
    public User getUser(Long user_id) {
        return userRepository.findById(user_id).get();
    }


    /**
     * 给用户添加积分
     */
    public void addUserIntegral(long user_id, int integral){
        User user = userRepository.findById(user_id).get();
        user.setIntegral(user.getIntegral() + integral);
        user.setUpdate_time(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

    /**
     * 给用户设置 VIP
     */
    public void addUserVip(long user_id, int vip_grade){
        User user = userRepository.findById(user_id).get();
        user.setVip_grade(vip_grade);
        user.setUpdate_time(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

}

