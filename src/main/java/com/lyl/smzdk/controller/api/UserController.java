package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @RequestMapping("/createUser")
    public BaseCallBack createUser(String number, String password, String name, Integer sex) {

        if (number.length() > 32){
            return failCallBack(10001, "用户名不能超过32个字符");
        }

        User user = new User();
        user.setNumber(number);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        Date timestamp = new Date(System.currentTimeMillis());
        user.setCreate_time(timestamp);
        user.setUpdate_time(timestamp);

        User save = userRepository.save(user);

        return successCallBack(save);
    }

    /**
     * 更新数据库字段，只要某个字段传了值，就更新数据库
     */
    @RequestMapping("/updateUser")
    public BaseCallBack updateUser(Long user_id, String number, String name, String icon, String signature, Integer sex, String birth, String phone, String email, String province, String city) {
        BaseCallBack callBack;
        User user = userRepository.findById(user_id).get();

        if (!MyUtils.isEmpty(number)){
            user.setNumber(number);
        }
        if (!MyUtils.isEmpty(name)){
            user.setName(name);
        }
        if (!MyUtils.isEmpty(icon)){
            user.setIcon(icon);
        }
        if (!MyUtils.isEmpty(signature)){
            user.setSignature(signature);
        }
        if (sex == 1){
            user.setSex(sex);
        }
        if (!MyUtils.isEmpty(birth)){
            user.setBirth(birth);
        }
        if (!MyUtils.isEmpty(phone)){
            user.setPhone(phone);
        }
        if (!MyUtils.isEmpty(email)){
            user.setEmail(email);
        }
        if (!MyUtils.isEmpty(province)){
            user.setProvince(province);
        }
        if (!MyUtils.isEmpty(city)){
            user.setCity(city);
        }

        Date timestamp = new Date(System.currentTimeMillis());
        user.setUpdate_time(timestamp);

        userRepository.save(user);

        return successCallBack(user);
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

