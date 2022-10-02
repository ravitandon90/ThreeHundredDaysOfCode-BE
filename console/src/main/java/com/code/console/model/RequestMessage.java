package com.code.console.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestMessage {
    private String type;
    private String command;
}