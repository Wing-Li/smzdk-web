package com.lyl.smzdk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 会员充值记录表
 */
@Entity
public class VipRecharge {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private int user_id;
    @Column(nullable = false)
    private int money;
    private int vip_grade = 1;
    private int duration = 30;
    private int from = 0;
    private Date create_time = new Date(System.currentTimeMillis());

    public Long getId() {
        return id;
    }

    public VipRecharge(int user_id, int money, int vip_grade, int duration, int from, Date create_time) {
        this.user_id = user_id;
        this.money = money;
        this.vip_grade = vip_grade;
        this.duration = duration;
        this.from = from;
        this.create_time = create_time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
