package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateSessionHTTPRequest {
    private String userId;
    private String sessionId;
    private String problemId;
    private String language;
    private String solutionCode;
}