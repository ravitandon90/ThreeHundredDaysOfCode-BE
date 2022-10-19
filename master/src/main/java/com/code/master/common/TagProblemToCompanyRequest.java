package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TagProblemToCompanyRequest {
    private String userId;
    private String companyName;
    private String problemId;
}
