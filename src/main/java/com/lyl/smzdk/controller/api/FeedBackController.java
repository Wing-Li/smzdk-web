package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.Announcement;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.model.Feedback;
import com.lyl.smzdk.repository.FeedbackRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 建议反馈
 */
@RestController
public class FeedBackController extends ApiBaseController {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedBackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * 添加建议
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param title    标题
     * @param content  内容
     * @return
     */
    @PostMapping("addFeedBack")
    public BaseCallBack addFeedBack(Long userId, String userName, String title, String content) {

        // 标题和内容不能为空
        if (MyUtils.isEmpty(title)) {
            return failCallBack(StatusCode.BASE_TITLE_EMPYT, StatusCode.BASE_TITLE_EMPYT_TEXT);
        }
        if (MyUtils.isEmpty(content)) {
            return failCallBack(StatusCode.BASE_CONTENT_EMPYT, StatusCode.BASE_CONTENT_EMPYT_TEXT);
        }

        Feedback feedback = new Feedback(userId, userName, title, content);
        feedbackRepository.save(feedback);

        return successCallBack("添加成功");
    }

    /**
     * 管理员给用户的回复
     *
     * @param feedbackId    建议 的 id
     * @param reply 回复内容
     * @return
     */
    @PostMapping("addFeedBackReply")
    public BaseCallBack addFeedBackReply(Long feedbackId, String reply) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
        if (feedbackOptional.isPresent()) {
            Feedback feedback = feedbackOptional.get();
            feedback.setAdminReply(reply);
            feedbackRepository.save(feedback);

            return successCallBack("回复成功");
        } else {

            return failCallBack(StatusCode.BASE_REQUEST_EMPYT, StatusCode.BASE_REQUEST_EMPYT_TEXT);
        }
    }

    /**
     * 获取所有的建议
     */
    @PostMapping("getAllFeedback")
    public BaseCallBack getAllFeedback() {
        List<Feedback> all = feedbackRepository.findAll();
        return successCallBack(all);
    }
}
