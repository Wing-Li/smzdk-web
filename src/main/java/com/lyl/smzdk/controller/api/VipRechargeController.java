package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.VipRecharge;
import com.lyl.smzdk.repository.VipRechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
public class VipRechargeController extends ApiBaseController {

    private final VipRechargeRepository vipRechargeRepository;

    @Autowired
    public VipRechargeController(VipRechargeRepository vipRechargeRepository) {
        this.vipRechargeRepository = vipRechargeRepository;
    }

    @RequestMapping("/addVipRecharge")
    public void addVipRecharge(int user_id, int money, int vip_grade, int duration, int from, Date create_time){

    }
}
