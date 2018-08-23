package com.lyl.smzdk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * 表说明文档：https://docs.qq.com/sheet/BqI21X2yZIht1AtA8J1uFmNo1tq5ki0Hzm6i0w8oDA1vW3zb0IQmKC2Cjyb92hJmjH2k08Mj0gv2rG3tvCFu0
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;
    @Column(nullable = false)
    private String password = "";
    @Column(unique = true, nullable = false)
    private String name; // 用户名
    private String icon = "";
    private String signature = "";
    @Column(nullable = false)
    private Integer sex = 0; // 性别
    private String birth = "";
    @Column(length = 11)
    private String phone = "";
    private String email = "";
    private String province = "";
    private String city = "";
    private Integer vipGrade = 0;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date vipLimitDate = new Date(0);// 会员过期时间
    private Long integral = 0L;
    private Integer closeDays = 0;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User() {
    }

    public Date getVipLimitDate() {
        return vipLimitDate;
    }

    public void setVipLimitDate(Date vipLimitDate) {
        this.vipLimitDate = vipLimitDate;
    }

    public Integer getCloseDays() {
        return closeDays;
    }

    public void setCloseDays(Integer closeDays) {
        this.closeDays = closeDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getVipGrade() {
        return vipGrade;
    }

    public void setVipGrade(Integer vipGrade) {
        this.vipGrade = vipGrade;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
