package com.code.master.utils;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// Contains publicly accessible utility methods.
public class Utils {
    public static boolean IsSuccess(JSONObject object) {
        if (!object.has("message")) return false;
        return object.getString("message").equalsIgnoreCase("SUCCESS");
    }

    public static String GetOutput(JSONObject object) {
        if (!object.has("output")) return "";
        return object.getString("output");
    }

    public static String Encode(String in) {
        return String.valueOf(Base64.getEncoder().encode(in.getBytes(StandardCharsets.UTF_8)));
    }

    public static String Decode(String in) {
        return String.valueOf(Base64.getDecoder().decode(in));
    }
}
