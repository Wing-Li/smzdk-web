package com.lyl.smzdk.controller.api;


import com.lyl.smzdk.utils.JsonBuilder;
import com.lyl.smzdk.utils.TokenHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛云 Token 获取
 */
@RestController
public class QiniuTokenController extends ApiBaseController {

    @PostMapping("/token")
    public String token(String accessKey, String secretKey, String bucket) {
        String result = "";
        if (accessKey == null || secretKey == null || bucket == null) {
            result = BAD("参数不能为空").getJson();
            return result;
        }

        TokenHelper tokenHelper = TokenHelper.create(accessKey, secretKey);
        String token = tokenHelper.getToken(bucket);
        String json = buildToken(token).getJson();

        return json;
    }

    @GetMapping("/token")
    public String token() {
        return BAD("七牛token获取器，请使用POST方法请求该页面").getJson();
    }

    private JsonBuilder buildToken(String token) {
        return getJsonBuilder(200, "success").put("token", token);
    }

    private JsonBuilder BAD(String info) {
        return getJsonBuilder(400, info);
    }

    private JsonBuilder getJsonBuilder(int status, String info) {
        return new JsonBuilder()
                .put("status", status)
                .put("info", info);
    }
}
