package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, String> {
    List<UserNotification> findAllByToUserIdOrderByCreatedAtDesc(String userId);
    @Query("SELECT COUNT(notification_id) FROM user_notification WHERE toUserId=?1 AND seen=false")
    int getCountNotifications(String userId);
}