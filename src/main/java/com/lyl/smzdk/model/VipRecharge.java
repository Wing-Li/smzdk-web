package com.lyl.smzdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(nullable = false)
    private Long user_id;
    @Column(nullable = false)
    private Double money;
    private Integer vipGrade = 1;
    private Integer duration = 1;
    private Integer fromRecharge = 0;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createTime;

    public Long getId() {
        return id;
    }

    public VipRecharge(){
    }

    public VipRecharge(Long user_id, Double money, int vipGrade, int duration, int from) {
        this.user_id = user_id;
        this.money = money;
        this.vipGrade = vipGrade;
        this.duration = duration;
        this.fromRecharge = from;
    }

    public void setVipGrade(Integer vipGrade) {
        this.vipGrade = vipGrade;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setFromRecharge(Integer fromRecharge) {
        this.fromRecharge = fromRecharge;
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

    public int getVipGrade() {
        return vipGrade;
    }

    public void setVip_grade(int vip_grade) {
        this.vipGrade = vip_grade;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFromRecharge() {
        return fromRecharge;
    }

    public void setFrom(int from) {
        this.fromRecharge = from;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
