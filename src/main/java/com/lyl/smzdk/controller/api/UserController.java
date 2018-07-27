package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 管理用户的逻辑
 */
@EnableAutoConfiguration
@RestController
public class UserController extends ApiBaseController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 创建用户
     */
    @RequestMapping(path = "/createUser", method = RequestMethod.GET)
    public BaseCallBack createUser(@RequestParam String number, @RequestParam String password, @RequestParam String name, @RequestParam Integer sex) {
        // 检查 用户名、密码、昵称、性别 是否符合规范
        if (MyUtils.isEmpty(number) || number.length() > 32 || number.length() < 2) {
            return failCallBack(StatusCode.USER_NAME_10001, StatusCode.USER_NAME_10001_TEXT);
        }
        if (MyUtils.isEmpty(password) || password.length() > 32 || password.length() < 8) {
            return failCallBack(StatusCode.USER_NAME_10002, StatusCode.USER_NAME_10002_TEXT);
        }
        if (MyUtils.isEmpty(name) || name.length() > 16) {
            return failCallBack(StatusCode.USER_NAME_10003, StatusCode.USER_NAME_10003_TEXT);
        }
        if (sex != 0) {
            sex = 1;
        }

        int byNumber = userRepository.countByNumber(number);
        if (byNumber > 0) {
            return failCallBack(StatusCode.USER_NAME_10004, StatusCode.USER_NAME_10004_TEXT);
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
    @PostMapping("/updateUser")
    public BaseCallBack updateUser(Long user_id, String number, String name, String icon, String signature, Integer sex, String birth, String phone, String email, String province, String city) {
        BaseCallBack callBack;
        User user = userRepository.findById(user_id).get();

        if (!MyUtils.isEmpty(number)) {
            user.setNumber(number);
        }
        if (!MyUtils.isEmpty(name)) {
            user.setName(name);
        }
        if (!MyUtils.isEmpty(icon)) {
            user.setIcon(icon);
        }
        if (!MyUtils.isEmpty(signature)) {
            user.setSignature(signature);
        }
        if (sex == 1) {
            user.setSex(sex);
        }
        if (!MyUtils.isEmpty(birth)) {
            user.setBirth(birth);
        }
        if (!MyUtils.isEmpty(phone)) {
            user.setPhone(phone);
        }
        if (!MyUtils.isEmpty(email)) {
            user.setEmail(email);
        }
        if (!MyUtils.isEmpty(province)) {
            user.setProvince(province);
        }
        if (!MyUtils.isEmpty(city)) {
            user.setCity(city);
        }

        Date timestamp = new Date(System.currentTimeMillis());
        user.setUpdate_time(timestamp);

        userRepository.save(user);

        return successCallBack(user);
    }

    /**
     * 登录
     *
     * @param user_name 用户号 或  手机号
     * @param password  密码
     * @return 用户信息
     */
    @PostMapping("/login")
    public BaseCallBack login(String user_name, String password) {
        if (!MyUtils.isEmpty(user_name) && !MyUtils.isEmpty(password)) {
            User user = userRepository.findByNumberOrPhone(user_name, user_name);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    return successCallBack(user);
                } else {
                    failCallBack(StatusCode.USER_NAME_11002, StatusCode.USER_NAME_11002_TEXT);
                }
            } else {
                return failCallBack(StatusCode.USER_NAME_11001, StatusCode.USER_NAME_11001_TEXT);
            }

        }
        return failCallBack(StatusCode.USER_NAME_11003, StatusCode.USER_NAME_11003_TEXT);
    }

    /**
     * 获取所有用户
     */
    @PostMapping("/getAllUser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * 获取用户信息
     */
    @PostMapping("/getUser")
    public User getUser(Long user_id) {
        return userRepository.findById(user_id).get();
    }


    /**
     * 给用户添加积分
     */
    public void addUserIntegral(long user_id, int integral) {
        User user = userRepository.findById(user_id).get();
        user.setIntegral(user.getIntegral() + integral);
        user.setUpdate_time(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

    /**
     * 给用户设置 VIP
     */
    public void addUserVip(long user_id, int vip_grade) {
        User user = userRepository.findById(user_id).get();
        user.setVip_grade(vip_grade);
        user.setUpdate_time(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

}

