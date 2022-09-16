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
        return GetLanguageFromId(languageIntId) ;
    }


    public static String GetLastWord(String input) {
        final String delimiter = " ";
        String[] values = input.split(delimiter);
        if (values.length == 0 ||
                input.isEmpty() ||
                input.charAt(input.length() - 1) == ' ') {
            return "";
        }
        return values[values.length - 1];
    }

    public static String GetWordMatch(String matchText, String searchText, int numberOfWords) {
        String wordMatch = "";
        final String lowerMatchText = matchText.toLowerCase();
        final String lowerSearchText = searchText.toLowerCase();
        String[] arr = lowerMatchText.split(" ");
        int index = -1;
        for (String str : arr) {
            ++index;
            if (str.indexOf(lowerSearchText) == -1)  continue;
            break;
        }
        for (int i = 0; i < numberOfWords && index + i < arr.length; ++i) {
            wordMatch += arr[index + i] + " ";
        }
        return wordMatch.trim();
    }
    public static String GetLanguageFromId(int languageIntId) {
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
