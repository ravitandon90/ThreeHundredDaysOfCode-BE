package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, String> {
    List<PostComment> findAllByPostId(String postId);
}