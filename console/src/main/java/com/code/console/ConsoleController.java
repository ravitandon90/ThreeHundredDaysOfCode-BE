package com.code.console;

import com.code.console.model.RequestMessage;
import com.code.console.model.ResponseMessage;
import com.code.console.ssh.SSHManager;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO(Ravi): Read through this document to enable security for Websocket access using the JWT token.
 * Link: https://github.com/spring-projects/spring-framework/blob/main/src/docs/asciidoc/web/websocket.adoc#token-authentication
 */

@RestController
public class ConsoleController {

    private SSHManager sshManager;

    public ConsoleController() {
        System.out.println("Connecting through SSH ......");
        this.sshManager = new SSHManager();
        String errorMessage = sshManager.connect();
        if (errorMessage == null) System.out.println("Connection successful.");
        else System.out.printf("Connection failed. Error: %s\n", errorMessage);
    }

    @MessageMapping("/all")
    @SendTo("/topic/all")
    public ResponseMessage send(RequestMessage message) {
        ResponseMessage responseMessage = new ResponseMessage();
        System.out.println("Received Message");
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        if (!sshManager.isConnected()) {
            System.out.printf("SSHManager is not connected as yet.");
            responseMessage.setStatus("Failure");
            responseMessage.setTime(time);
        } else {
            final String output = sshManager.sendCommand(message.getCommand() + "\n");
            System.out.printf("Command: {%s}, Output: {%s}\n", message.getCommand(), output);
            responseMessage.setStatus("Success");
            responseMessage.setTime(time);
            responseMessage.setResponse(output);
            responseMessage.setCommand(message.getCommand());
        }
        return responseMessage;
    }
}
