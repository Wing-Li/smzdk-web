package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.BtSearch;
import com.lyl.smzdk.repository.BtSearchRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class BtSearchController extends ApiBaseController {

    private final BtSearchRepository btSearchRepository;

    @Autowired
    public BtSearchController(BtSearchRepository btSearchRepository) {
        this.btSearchRepository = btSearchRepository;
    }

    /**
     * 是否允许搜索，并将搜索记录加入数据库
     *
     * @param userId 用户id
     * @param uuid    uuid
     * @param content 搜索内容
     * @return
     */
    @PostMapping("/btSearch")
    public BaseCallBack btSearch(Long userId, String uuid, String content) {

        // 搜索的内容为空
        if (MyUtils.isEmpty(content)){
            return failCallBack(StatusCode.USER_NAME_16001, StatusCode.USER_NAME_16001_TEXT);
        }

        // 今天 00.00.00 的日期
        Calendar instance = Calendar.getInstance();
        instance.set(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH),// 年月日
                0, 0, 0); // 时分秒
        Date time = instance.getTime();

        // 查询使用 userId 或者 uuid ，今天的查询次数
        int countSreach = 0;
        if (userId == null || userId == 0) {
            // 没有账号的人
            countSreach = btSearchRepository.countByUuidAndCreateTimeAfter(uuid, time);
        } else {
            // 有账号的
            countSreach = btSearchRepository.countByUserIdAndCreateTimeAfter(userId, time);
        }

        // 将搜索记录保存进数据库
        BtSearch btSearch = new BtSearch(userId, uuid, content);
        btSearchRepository.save(btSearch);

        return successCallBack(countSreach);
    }
}
