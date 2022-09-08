package com.code.master.data;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, String> {
    List<UserNotification> findAllByToUserIdAndByOrderByCreatedAtDesc(String userId);

}