package com.code.master.utils;

import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.assertEquals;

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
        String programFileName = "testData/cpp/Dijkstra.cpp";
        String inputFileName = "testData/cpp/inputDijkstra.txt";
        try {
            String program = Files.readString(Path.of(programFileName));
            String input = Files.readString(Path.of(inputFileName));
            int languageSelected = 54;
            object = cc.run(program, input, languageSelected, "");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
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
            int languageSelected = 1004;
            object = cc.run(program, input, languageSelected, "Sum");
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
            int languageSelected = 60;
            object = cc.run(program, input, languageSelected, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "21");
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
            int languageSelected = 71;
            object = cc.run(program, input, languageSelected, "Sum");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(object);
        assertEquals(object.get("message"), "Success");
        assertEquals(object.get("output"), "3");
    }
}
