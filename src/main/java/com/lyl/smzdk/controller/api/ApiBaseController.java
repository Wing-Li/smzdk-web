package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.BaseCallBack;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API 的基类，所有 API 都继承这个类
 */
@RequestMapping("/api")
public class ApiBaseController {

    /**
     * 请求数据成功
     */
    BaseCallBack successCallBack(Object t) {
        return new BaseCallBack(200, "请求成功", t);
    }

    /**
     * 请求失败时返回的数据
     *
     * @param code 失败 code
     * @param msg  失败信息
     * @return
     */
    BaseCallBack failCallBack(int code, String msg) {
        return new BaseCallBack(code, msg, "");
    }


}
