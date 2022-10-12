package com.code.console.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseMessage {
    public String status;
    public String response;
    public String time;
    public String command;

    public ResponseMessage() {
        status = "";
        response = "";
        time = "";
    }

    public ResponseMessage(String status, String response, String time) {
        this.status = status;
        this.response = response;
        this.time = time;
    }
}