package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProfileHTTPRequest {
    private String userId;
    private String emailId;
    private String userName;
    private String referrerId;
}
