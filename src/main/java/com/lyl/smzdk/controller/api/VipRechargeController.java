package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.model.VipRecharge;
import com.lyl.smzdk.repository.UserRepository;
import com.lyl.smzdk.repository.VipRechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


/**
 * 会员充值记录
 */
@Controller
public class VipRechargeController extends ApiBaseController {

    private final VipRechargeRepository vipRechargeRepository;
    private final UserRepository userRepository;

    @Autowired
    public VipRechargeController(VipRechargeRepository vipRechargeRepository, UserRepository userRepository) {
        this.vipRechargeRepository = vipRechargeRepository;
        this.userRepository = userRepository;
    }

    /**
     * 会员充值
     *
     * @param user_id   会员id
     * @param money     出的钱数
     * @param vip_grade vip等级
     * @param duration  充值时长
     * @param from      充值来源
     * @return
     */
    @RequestMapping("/addVipRecharge")
    public BaseCallBack addVipRecharge(Long user_id, Double money, int vip_grade, int duration, int from) {

        boolean user = userRepository.findById(user_id).isPresent();
        if (user) {
            // 用户不存在
            return failCallBack(StatusCode.USER_NAME_15001, StatusCode.USER_NAME_15001_TEXT);
        }

        // from = 3,是官方送的
        if (from != 3) {
            if (money < 0) {
                // 金额小于0
                return failCallBack(StatusCode.USER_NAME_15002, StatusCode.USER_NAME_15002_TEXT);
            }
        } else {
            money = 0D;
        }

        int time = duration * 30;
        VipRecharge vipRecharge = new VipRecharge(user_id, money, vip_grade, time, from);
        VipRecharge save = vipRechargeRepository.save(vipRecharge);

        return successCallBack(save);
    }
}
