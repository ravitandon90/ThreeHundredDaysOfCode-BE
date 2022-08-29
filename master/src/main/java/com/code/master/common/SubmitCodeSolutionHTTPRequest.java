package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitCodeSolutionHTTPRequest {
    private String userId;
    private String problemId;
    private String solutionCode;
    private int languageId;
}
