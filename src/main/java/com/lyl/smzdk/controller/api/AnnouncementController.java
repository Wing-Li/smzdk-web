package com.lyl.smzdk.controller.api;

import com.lyl.smzdk.config.StatusCode;
import com.lyl.smzdk.model.Announcement;
import com.lyl.smzdk.model.BaseCallBack;
import com.lyl.smzdk.repository.AnnouncementRepository;
import com.lyl.smzdk.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公告
 */
@RestController
public class AnnouncementController extends ApiBaseController {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    /**
     * 添加公告
     *
     * @param title      标题
     * @param authorName 作者名
     * @param content    内容
     * @return
     */
    @PostMapping("addAnnouncement")
    public BaseCallBack addAnnouncement(String title, String authorName, String content) {

        // 标题和内容不能为空
        if (MyUtils.isEmpty(title)) {
            return failCallBack(StatusCode.BASE_TITLE_EMPYT, StatusCode.BASE_TITLE_EMPYT_TEXT);
        }
        if (MyUtils.isEmpty(content)) {
            return failCallBack(StatusCode.BASE_CONTENT_EMPYT, StatusCode.BASE_CONTENT_EMPYT_TEXT);
        }

        Announcement announcement = new Announcement(title, content, authorName);
        announcementRepository.save(announcement);

        return successCallBack("添加成功");
    }

    /**
     * 获取最后一条公告
     */
    @PostMapping("getLastAnnouncement")
    public BaseCallBack getLastAnnouncement() {
        Announcement topOrderByCreateTimeDesc = announcementRepository.findTopByOrderByCreateTimeDesc();
        if (topOrderByCreateTimeDesc != null) {
            return successCallBack(topOrderByCreateTimeDesc);
        }
        return null;
    }

    /**
     * 获取所有的公告
     */
    @PostMapping("getAllAnnouncement")
    public BaseCallBack getAllAnnouncement() {
        List<Announcement> all = announcementRepository.findAll();
        return successCallBack(all);
    }

}
