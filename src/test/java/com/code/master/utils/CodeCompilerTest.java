package com.code.master.utils;

import com.code.master.common.Constants;
import com.code.master.judge.CodeCompiler;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.*;

public class CodeCompilerTest {
    @Test
    public void basicTest() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = cc.run("", "", 0, "");
        assertEquals(object, null);
    }

    @Test
    public void cppCorrectCode() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/cpp/Sum.cpp";
        String inputFileName = "testData/cpp/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_CPP_CODE, "");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void cppCompilationError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/cpp/SumCompilerError.cpp";
        String inputFileName = "testData/cpp/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_CPP_CODE, "");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void cppRuntimeError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/cpp/SumRuntimeError.cpp";
        String inputFileName = "testData/cpp/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_CPP_CODE, "");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void javaCorrectCode() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/java/Sum.java";
        String inputFileName = "testData/java/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_JAVA_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void javaCompilationError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/java/SumCompilationError.java";
        String inputFileName = "testData/java/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_JAVA_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void javaRuntimeError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/java/SumRuntimeError.java";
        String inputFileName = "testData/java/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_JAVA_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void goCorrectCode() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/go/Sum.go";
        String inputFileName = "testData/go/inputSum.txt";

        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_GO_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void goCompilerError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/go/SumCompilationError.go";
        String inputFileName = "testData/go/inputSum.txt";

        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_GO_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void pythonCorrectProgram() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/python/Sum.py";
        String inputFileName = "testData/python/inputSum.txt";

        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_PYTHON_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void pythonCompilationError() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/python/SumCompilationError.py";
        String inputFileName = "testData/python/inputSum.txt";

        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_PYTHON_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Error");
        assertTrue(object.getString("output").length() > 0);
    }

    @Test
    public void jsWorkingCode() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/javascript/sum.js";
        String inputFileName = "testData/javascript/inputSum.txt";

        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_JS_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void phpWorkingCode() {
        CodeCompiler cc = new CodeCompiler();
        JSONObject object = null;
        String programFileName = "testData/php/sum.php";
        String inputFileName = "testData/php/inputSum.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            object = cc.run(program, input, Constants.LANGUAGE_PHP_CODE, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }
}
