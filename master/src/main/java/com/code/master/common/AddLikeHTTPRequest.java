package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLikeHTTPRequest {
    private String postId;
    private String userId;
    private String isLiked;
}
