package com.lyl.smzdk.repository;

import com.lyl.smzdk.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Announcement findTopByOrderByCreateTimeDesc();
}
