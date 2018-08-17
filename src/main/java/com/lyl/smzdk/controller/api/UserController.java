package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.repository.UserRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


/**
 * 管理用户的逻辑
 */
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
     * @param number   账户名
     * @param password 密码
     * @param name     昵称
     * @param sex      性别
     * @return 返回用户信息
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public BaseCallBack createUser(String number, String password, String name, Integer sex) {
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
        if (sex != null && sex != 0) {
            sex = 1;
        } else {
            sex = 0;
        }

        // 用户名 和 昵称 都不能重复
        int byNumber = userRepository.countByNumber(number);
        if (byNumber > 0) {
            return failCallBack(StatusCode.USER_NAME_10004, StatusCode.USER_NAME_10004_TEXT);
        }
        int byName = userRepository.countByName(name);
        if (byName > 0) {
            return failCallBack(StatusCode.USER_NAME_10005, StatusCode.USER_NAME_10005_TEXT);
        }

        try {
            User user = new User();
            user.setNumber(number);
            user.setPassword(password);
            user.setName(name);
            user.setSex(sex);

            User save = userRepository.save(user);

            return successCallBack(save);
        } catch (Exception e) {
            return failCallBack(StatusCode.USER_NAME_10000, StatusCode.USER_NAME_10000_TEXT);
        }
    }

    /**
     * 更新数据库字段，只要某个字段传了值，就更新数据库
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public BaseCallBack updateUser(Long userId, String name, String icon, String signature, Integer sex, String birth, String phone, String email, String province, String city) {
        User user = userRepository.findById(userId).get();

        if (!MyUtils.isEmpty(name) && !name.equals(user.getName())) {
            int byName = userRepository.countByName(name);
            if (byName > 0) {
                return failCallBack(StatusCode.USER_NAME_10005, StatusCode.USER_NAME_10005_TEXT);
            } else {
                user.setName(name);
            }
        }
        if (!MyUtils.isEmpty(icon) && !icon.equals(user.getIcon())) {
            user.setIcon(icon);
        }
        if (!MyUtils.isEmpty(signature) && !signature.equals(user.getSignature())) {
            user.setSignature(signature);
        }
        if (sex != null && sex.intValue() != user.getSex().intValue()) {
            user.setSex(sex);
        }
        if (!MyUtils.isEmpty(birth) && !birth.equals(user.getBirth())) {
            user.setBirth(birth);
        }
        if (!MyUtils.isEmpty(phone) && !phone.equals(user.getPhone())) {
            user.setPhone(phone);
        }
        if (!MyUtils.isEmpty(email) && !email.equals(user.getEmail())) {
            user.setEmail(email);
        }
        if (!MyUtils.isEmpty(province) && !province.equals(user.getProvince())) {
            user.setProvince(province);
        }
        if (!MyUtils.isEmpty(city) && !city.equals(user.getCity())) {
            user.setCity(city);
        }

        userRepository.save(user);

        return successCallBack(user);
    }

    /**
     * 登录
     *
     * @param userName 用户号 或  手机号
     * @param password 密码
     * @return 用户信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseCallBack login(String userName, String password) {
        if (!MyUtils.isEmpty(userName) && !MyUtils.isEmpty(password)) {
            User user = userRepository.findByNumberOrPhone(userName, userName);
            if (user != null) {
                if (user.getCloseDays() > 0) {
                    // 账号被封
                    return failCallBack(StatusCode.USER_NAME_13001, StatusCode.USER_NAME_13001_TEXT + user.getCloseDays());
                } else if (password.equals(user.getPassword())) {
                    // 登录成功
                    return successCallBack(user);
                } else {
                    // 密码不对
                    return failCallBack(StatusCode.USER_NAME_11002, StatusCode.USER_NAME_11002_TEXT);
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
    public BaseCallBack getAllUser() {
        return successCallBack(userRepository.findAll());
    }

    /**
     * 获取用户信息
     */
    @PostMapping("/getUser")
    public BaseCallBack getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User userTable = user.get();
            if (userTable.getCloseDays() > 0) {
                // 账号被封
                return failCallBack(StatusCode.USER_NAME_13001, StatusCode.USER_NAME_13001_TEXT + userTable.getCloseDays());
            } else {
                // 获取成功

                // 如果当前是会员，检查会员是否过期
                if (userTable.getVipGrade() >= 2){
                    long vipLimitDate = userTable.getVipLimitDate().getTime();
                    long nowTime = new Date().getTime();
                    // 过期时间 小于 当前时间，将会员等级设计会普通
                    if (vipLimitDate < nowTime){
                        userTable.setVipGrade(1);
                        userTable = userRepository.save(userTable);
                    }
                }

                return successCallBack(userTable);
            }
        } else {
            return failCallBack(StatusCode.USER_NAME_11001, StatusCode.USER_NAME_11001_TEXT);
        }
    }
}

