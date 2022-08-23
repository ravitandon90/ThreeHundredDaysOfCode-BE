package com.code.master.utils;

import com.code.master.common.Constants;
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
    public void basicTestCpp() {
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
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }

    @Test
    public void basicTestErrorCpp() {
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
    }

    @Test
    public void basicTestJava() {
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
    public void basicTestGo() {
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
    public void basicTestPython() {
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
}
