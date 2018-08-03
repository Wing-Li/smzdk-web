package com.lyl.smzdk.repository;

import com.lyl.smzdk.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
