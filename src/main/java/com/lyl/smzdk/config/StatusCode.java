package com.lyl.smzdk.config;

public class StatusCode {

    public final static int USER_NAME_10000 = 10000;
    public final static String USER_NAME_10000_TEXT = "网络异常，请稍后再试";

    // 创建用户
    public final static int USER_NAME_10001 = 10001;
    public final static String USER_NAME_10001_TEXT = "用户名必须在2到32位字符之间";
    public final static int USER_NAME_10002 = 10002;
    public final static String USER_NAME_10002_TEXT = "密码必须在8到32位字符之间";
    public final static int USER_NAME_10003 = 10003;
    public final static String USER_NAME_10003_TEXT = "昵称不能超过16个字符";
    public final static int USER_NAME_10004 = 10004;
    public final static String USER_NAME_10004_TEXT = "用户名已经存在";
    public final static int USER_NAME_10005 = 10005;
    public final static String USER_NAME_10005_TEXT = "昵称已经存在";

    // 更新用户
    public final static int USER_NAME_11001 = 11001;
    public final static String USER_NAME_11001_TEXT = "没有此用户";
    public final static int USER_NAME_11002 = 11002;
    public final static String USER_NAME_11002_TEXT = "密码错误";
    public final static int USER_NAME_11003 = 11003;
    public final static String USER_NAME_11003_TEXT = "用户名密码不能为空";

    // 修改密码 12

    // 查询用户 13
    public final static int USER_NAME_13001 = 11001;
    public final static String USER_NAME_13001_TEXT = "您的账户被限制登录(天)：";

    // 查询用户列表 14

    // 会员充值 15
    public final static int USER_NAME_15001 = 15001;
    public final static String USER_NAME_15001_TEXT = "没有此用户，请确认用户信息";
    public final static int USER_NAME_15002 = 15002;
    public final static String USER_NAME_15002_TEXT = "金额不能小于0元";

}
