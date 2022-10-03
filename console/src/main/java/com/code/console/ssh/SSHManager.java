package com.code.console.ssh;

import com.jcraft.jsch.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

public class SSHManager {
    @Getter
    @Setter
    private boolean isConnected = false;
    private JSch jschSSHChannel;
    private String strUserName;
    private String strConnectionIP;
    private int intConnectionPort  = 22;
    private String strPassword;
    private Session sesConnection;
    private int intTimeOut = 60000;
    private String privateKey = "/Users/ravi/.ssh/id_ed25519";
    private String userName = "ravi";
    private String host = "localhost";

    public SSHManager(String knownHostsFileName) {
        jschSSHChannel = new JSch();
        try {
            jschSSHChannel.setKnownHosts(knownHostsFileName);
            jschSSHChannel.addIdentity(privateKey);
            isConnected = true;
        } catch (JSchException jschX) {
            logError(jschX.getMessage());
        }
    }

    public String connect() {
        String errorMessage = null;
        try {
            sesConnection = jschSSHChannel.getSession(userName, host, intConnectionPort);
            sesConnection.setPassword(strPassword);
            // UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
            // sesConnection.setConfig("StrictHostKeyChecking", "no");
            sesConnection.connect(intTimeOut);
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

    private String logError(String errorMessage) {
        if (errorMessage != null) {
            System.out.printf("{0}:{1} - {2}", new Object[]{strConnectionIP, intConnectionPort, errorMessage});
        }
        return errorMessage;
    }
    private String logWarning(String warnMessage) {
        if (warnMessage != null) {
            System.out.printf("{0}:{1} - {2}", new Object[]{strConnectionIP, intConnectionPort, warnMessage});
        }
        return warnMessage;
    }
}