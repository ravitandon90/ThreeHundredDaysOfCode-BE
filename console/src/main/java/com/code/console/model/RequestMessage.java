package com.code.console.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestMessage {
    private String method;
    private String command;
}