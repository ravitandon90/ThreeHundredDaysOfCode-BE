package com.code.console;

import com.code.console.model.RequestMessage;
import com.code.console.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleController {
    @MessageMapping("/console")
    @SendTo("/topic/command")
    public ResponseMessage send(RequestMessage message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ResponseMessage("Success", message.getCommand(), time);
    }
}
