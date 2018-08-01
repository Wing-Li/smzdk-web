package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.repository.BtSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BtSearchController {

    private final BtSearchRepository btSearchRepository;

    @Autowired
    public BtSearchController(BtSearchRepository btSearchRepository) {
        this.btSearchRepository = btSearchRepository;
    }

    /**
     * 是否允许搜索，并将搜索记录加入数据库
     *
     * @param user_id 用户id
     * @param uuid    uuid
     * @param content 搜索内容
     * @return
     */
//    @PostMapping("/btSearch")
//    public BaseCallBack btSearch(Long user_id, String uuid, String content) {
//        // 没有账号的人
//        if (user_id == null || user_id == 0) {
//
//        } else {
//            // 有账号的
//        }
//
//
//    }
}
