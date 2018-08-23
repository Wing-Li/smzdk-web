package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.model.VipRecharge;
import com.lyl.smzdk.repository.UserRepository;
import com.lyl.smzdk.repository.VipRechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

/**
 * 会员充值记录
 */
@RestController
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
     * @param userId   会员id
     * @param money    出的钱数
     * @param vipGrade vip等级
     * @param duration 充值时长（天）
     * @param from     充值来源
     * @return
     */
    @PostMapping("/addVipRecharge")
    public BaseCallBack addVipRecharge(Long userId, Double money, Integer vipGrade, Integer duration, Integer from) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
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

        VipRecharge vipRecharge = new VipRecharge(userId, money, vipGrade, duration, from);
        // 将数据保存到数据库
        VipRecharge save = vipRechargeRepository.save(vipRecharge);

        // 给用户设置会员
        User userTable = user.get();
        userTable.setVipGrade(vipGrade);

        // 设置用户的到期时间
        // 先获取以前时间，查看他是否过期，没过期继续加。过期了，或者没有，从新设置
        Date vipLimitDate = userTable.getVipLimitDate();
        Calendar nowCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Date nowTime = nowCalendar.getTime();

        if (vipLimitDate != null && vipLimitDate.getTime() > nowTime.getTime()) {
            // 以前冲过会员，并且还有没有过期，在他的时间往后加
            vipLimitDate.setDate(vipLimitDate.getDate() + duration);
            userTable.setVipLimitDate(vipLimitDate);
        } else {
            // 以前没有冲过会员 或者 过期了，时间从现在开始算
            nowTime.setDate(nowTime.getDate() + duration);
            userTable.setVipLimitDate(nowTime);
        }
        User resultUser = userRepository.save(userTable);

        return successCallBack(userAdapter(resultUser));
    }
}
