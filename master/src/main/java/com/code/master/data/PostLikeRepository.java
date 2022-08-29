package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, String> {

    List<PostLike> findAllByPostId(String postId);
}