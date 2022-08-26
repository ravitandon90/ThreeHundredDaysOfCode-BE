package com.code.master.utils;

import org.testng.annotations.Test;

import java.util.Base64;

public class UtilsTest {

    @Test
    public void sourceCodeDecode() {
        final String stdIn = "MTIKNQpOTwo=";
        final String stdInDecoded = Utils.Decode(stdIn);
        System.out.println(stdInDecoded);
    }

    @Test
    public void t() {
        String s = "3";
        String encoded = new String(Base64.getEncoder().encode(s.getBytes()));
        String decoded = new String(Base64.getDecoder().decode(encoded));
        System.out.println("S: " + s + " -> " + encoded + " -> " + decoded);
    }
}
