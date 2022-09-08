package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, String> {
    List<UserPost> findAllByOrderByCreatedAtDesc();
    UserPost getByPostId(String postId);
}