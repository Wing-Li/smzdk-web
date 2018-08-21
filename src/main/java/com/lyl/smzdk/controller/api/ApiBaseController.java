package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.User;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API 的基类，所有 API 都继承这个类
 */
@RequestMapping("/api")
public class ApiBaseController {

    /**
     * 请求数据成功
     */
    BaseCallBack<Object> successCallBack(Object t) {
        return new BaseCallBack<>(200, "请求成功", t);
    }

    /**
     * 请求失败时返回的数据
     *
     * @param code 失败 code
     * @param msg  失败信息
     * @return
     */
    BaseCallBack<Object> failCallBack(int code, String msg) {
        return new BaseCallBack<>(code, msg, null);
    }


    /**
     * 将用户信息返回给 客户端时，需要处理的一些逻辑
     */
    protected User userAdapter(User user) {
        if (!MyUtils.isEmpty(user.getIcon())) {
            // 设置头像
            user.setIcon(UserController.ICON_HOST + user.getIcon());
        }

        return user;
    }
}
