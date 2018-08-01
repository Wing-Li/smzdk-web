package com.lyl.smzdk.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 会员充值记录表
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class VipRecharge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Long user_id;
    @Column(nullable = false)
    private Double money;
    private Integer vip_grade = 1;
    private Integer duration = 1;
    private Integer from_recharge = 0;
    @CreatedDate
    private Date create_time;

    public Long getId() {
        return id;
    }

    public VipRecharge(){
    }

    public VipRecharge(Long user_id, Double money, int vip_grade, int duration, int from) {
        this.user_id = user_id;
        this.money = money;
        this.vip_grade = vip_grade;
        this.duration = duration;
        this.from_recharge = from;
    }

    public void setVip_grade(Integer vip_grade) {
        this.vip_grade = vip_grade;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setFrom_recharge(Integer from_recharge) {
        this.from_recharge = from_recharge;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getVip_grade() {
        return vip_grade;
    }

    public void setVip_grade(int vip_grade) {
        this.vip_grade = vip_grade;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFrom_recharge() {
        return from_recharge;
    }

    public void setFrom(int from) {
        this.from_recharge = from;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
