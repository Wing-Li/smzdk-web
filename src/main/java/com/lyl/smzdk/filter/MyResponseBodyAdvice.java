package com.lyl.smzdk.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyl.smzdk.utils.DESHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对返回的数据进行加密
 * 参考：https://blog.csdn.net/huang812561/article/details/79424041
 *
 * @author huang_guoqiang
 * @desc 返回数据加密
 * @date 2018/3/2 11:12
 */
// classpath:config/my.properties指的是src/main/resources目录下config目录下的my.properties文件
@PropertySource({"classpath:application.properties"})
@ControllerAdvice(basePackages = "com.lyl.smzdk.controller")
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    @Value("${spring.profiles.active}")
    private String active;

    private final static Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // dev 环境不加密
        if (!"dev".equals(active)) {
            // 对所有的 api 数据都加密
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
                return DESHelper.encrypt(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }
}
