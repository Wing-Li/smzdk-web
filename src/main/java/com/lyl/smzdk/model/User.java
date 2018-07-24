package com.lyl.smzdk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * 用户信息表
 * 表说明文档：https://docs.qq.com/sheet/BqI21X2yZIht1AtA8J1uFmNo1tq5ki0Hzm6i0w8oDA1vW3zb0IQmKC2Cjyb92hJmjH2k08Mj0gv2rG3tvCFu0
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String number;
    @Column(unique = true, nullable = false)
    private String name;
    private String icon;
    private String signature;
    @Column(nullable = false)
    private int sex = 0;
    private String birth;
    @Column(length = 11)
    private String phone;
    private String email;
    private String province;
    private String city;
    private int vip_grade = 0;
    private long integral = 0;
    private Date create_time = new Date(System.currentTimeMillis());
    private Date update_time = new Date(System.currentTimeMillis());

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User() {
    }

    public User(String number, String name, String icon, String signature, int sex, String birth, String phone, String email, String province, String city, int vip_grade, long integral, Date create_time, Date update_time) {
        this.number = number;
        this.name = name;
        this.icon = icon;
        this.signature = signature;
        this.sex = sex;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.province = province;
        this.city = city;
        this.vip_grade = vip_grade;
        this.integral = integral;
        this.create_time = create_time;
        this.update_time = update_time;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public int getVip_grade() {
        return vip_grade;
    }

    public void setVip_grade(int vip_grade) {
        this.vip_grade = vip_grade;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
