package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddCommentHTTPRequest {
    private String userId;
    private String text;
    private String postId;
}
