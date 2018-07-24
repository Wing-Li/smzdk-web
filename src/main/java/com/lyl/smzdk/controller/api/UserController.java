package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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
    @RequestMapping("/saveUser")
    public void createUser(String number, String name, String icon, String signature, int sex, String birth, String phone, String email, String province, String city) {
        User user = new User();

        Date now_time = new Date(System.currentTimeMillis());
        user.setCreate_time(now_time);
        user.setUpdate_time(now_time);

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
    public User getUser(String name) {
        return userRepository.findByUserName(name);
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

