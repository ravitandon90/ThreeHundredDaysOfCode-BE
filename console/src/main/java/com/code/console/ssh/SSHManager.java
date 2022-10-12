package com.code.console.ssh;

import com.jcraft.jsch.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

public class SSHManager {
    @Getter @Setter
    private boolean isConnected = false;
    private final JSch jschSSHChannel;
    private final int intConnectionPort  = 22;
    private Session sesConnection;

    public SSHManager() {
        jschSSHChannel = new JSch();
        try {
            final String privateKey = "/Users/ravi/.ssh/id_rsa";
            jschSSHChannel.addIdentity(privateKey);
        } catch (JSchException jschX) {
            logError(jschX.getMessage());
        }
    }

    public String connect() {
        String errorMessage = null;
        try {
            String userName = "ravi";
            String host = "localhost";
            sesConnection = jschSSHChannel.getSession(userName, host, intConnectionPort);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            sesConnection.setConfig(config);
            // UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
            // sesConnection.setConfig("StrictHostKeyChecking", "no");
            int intTimeOut = 60000;
            sesConnection.connect(intTimeOut);
            isConnected = true;
        } catch (JSchException jschX) {
            errorMessage = jschX.getMessage();
        }
        return errorMessage;
    }
    public String sendCommand(String command) {
        StringBuilder outputBuffer = new StringBuilder();
        try {
            Channel channel = sesConnection.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();
            while (readByte != 0xffffffff) {
                outputBuffer.append((char) readByte);
                readByte = commandOutput.read();
            }
            channel.disconnect();
        } catch (IOException ioX) {
            logWarning(ioX.getMessage());
            return null;
        } catch (JSchException jschX) {
            logWarning(jschX.getMessage());
            return null;
        }
        return outputBuffer.toString();
    }
    public void close() {
        sesConnection.disconnect();
    }

    private String logError(String message) {
        if (message != null) {
            System.out.printf("{%s}:{%s} - {%s}", "localhost", intConnectionPort, message);
        }
        return message;
    }
    private String logWarning(String message) {
        if (message != null) {
            System.out.printf("{%s}:{%s} - {%s}", "localhost", intConnectionPort, message);
        }
        return message;
    }
}