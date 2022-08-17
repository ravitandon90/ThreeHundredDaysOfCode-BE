package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubmitCodeHTTPRequest {
    private String userId;
    private String problemName;
    private String problemLink;
    private String solutionLink;
}