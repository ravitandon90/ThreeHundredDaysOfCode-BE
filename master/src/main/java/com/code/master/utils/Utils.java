package com.code.master.utils;

import com.code.master.common.Constants;
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
        return new String(Base64.getEncoder().encode(in.getBytes()));
    }

    public static String Decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }

    public static String GetLanguageFromId(String languageId) {
        Integer languageIntId = Integer.parseInt(languageId);
        switch (languageIntId) {
            case Constants.LANGUAGE_CPP_CODE:
                return "cpp";

            case Constants.LANGUAGE_JAVA_CODE:
            case Constants.LANGUAGE_JAVA_CODE_2:
                return "java";

            case Constants.LANGUAGE_GO_CODE:
                return "golang";

            case Constants.LANGUAGE_PYTHON_CODE:
                return "python3";

            case Constants.LANGUAGE_PHP_CODE:
                return "php";

            case Constants.LANGUAGE_JS_CODE:
                return "javascript";

        }
        return "";
    }
}
