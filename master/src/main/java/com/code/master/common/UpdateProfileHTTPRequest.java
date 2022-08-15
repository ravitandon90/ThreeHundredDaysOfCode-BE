package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProfileHTTPRequest {
    private String name;
    private String email;
    private String org;
}
