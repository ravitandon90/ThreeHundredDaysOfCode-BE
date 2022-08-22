package com.code.master.utils;

import org.json.JSONObject;

import java.io.*;

public class CodeCompiler {
    public JSONObject run(String sourceCode, String input, int selectedLanguage, String className)  {
        JSONObject result = null;
        try {
            switch (selectedLanguage) {
                case (54):
                    result = runCodeCpp(sourceCode, input);
                    break;

                case (60):
                    result = runCodeGo(sourceCode, input);
                    break;

                case (71):
                    result = runCodePython(sourceCode, input);
                    break;

                case (1004):
                    result = runCodeJava(sourceCode, input, className);
                    break;

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }

    private JSONObject runCodeCpp(String sourceCode, String input) throws IOException {
        String output = "";
        try {
            // Step-I: Write the program to the tempFile.
            File sourceCodeFile = CreateTempFile(sourceCode);
            try {
                final String exeName = "/tmp/out";
                // Step-II: Compile the program.
                CompileFileWithCpp(String.valueOf(sourceCodeFile), exeName);
                // Step-III: Execute the program.
                output = RunCppExecutable(exeName, input);
            } catch (IOException e) {
                System.out.println(e);
            }
            sourceCodeFile.delete();
        } catch (IOException e) {
            System.out.println(e);
        }
        return new JSONObject().put("message", "Success").put("output", output);
    }

    private JSONObject runCodeJava(String sourceCode, String input, String className) throws IOException {
        String output = "";
        File classFile = null;
        File sourceCodeFile = null;
        try {
            // Step-I: Copy the source code to a temporary file.
            sourceCodeFile = CreateFile(sourceCode, className, ".java");
            try {
                // Step-II: Compile the file to generate the Main class.
                classFile = CompileFileWithJava(String.valueOf(sourceCodeFile), className);
                // Step-III: Run the executable
                output = RunJavaExecutable(className, input);
                classFile.delete();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // Step-IV: Clean up all the generated files.
        finally {
            if (sourceCodeFile != null) sourceCodeFile.delete();
            if (classFile != null) classFile.delete();
        }
        return new JSONObject().put("message", "Success").put("output", output);
    }

    private JSONObject runCodePython(String sourceCode, String input) throws IOException {
        String output = "";

        File sourceCodeFile = null;
        try {
            // Step-I: Copy the source code to a temporary file.
            sourceCodeFile = CreateFile(sourceCode, "Main", ".py");
            System.out.println(sourceCodeFile);
            try {
                // Step-III: Run the executable
                output = RunPythonExecutable(String.valueOf(sourceCodeFile), input);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // Step-IV: Clean up all the generated files.
        finally {
            if (sourceCodeFile != null) sourceCodeFile.delete();
        }
        return new JSONObject().put("message", "Success").put("output", output);
    }

    private JSONObject runCodeGo(String sourceCode, String input) throws IOException {
        String output = "";

        File sourceCodeFile = null;
        try {
            // Step-I: Copy the source code to a temporary file.
            sourceCodeFile = CreateFile(sourceCode, "Main", ".go");
            System.out.println(sourceCodeFile);
            try {
                // Step-III: Run the executable
                output = RunGoExecutable(String.valueOf(sourceCodeFile), input);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // Step-IV: Clean up all the generated files.
        finally {
           if (sourceCodeFile != null) sourceCodeFile.delete();
        }
        return new JSONObject().put("message", "Success").put("output", output);
    }


    private File CreateTempFile(String sourceCode) throws IOException {
        File tempFile = new File("Sum.java");
        FileWriter fileWriter = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(sourceCode);
        bw.close();
        return tempFile;
    }

    private File CreateFile(String sourceCode, String className, String suffix) throws IOException {
        File tempFile = new File("/tmp/" + className + suffix);
        FileWriter fileWriter = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(sourceCode);
        bw.close();
        return tempFile;
    }

    private void CompileFileWithCpp(String sourceCodeFile, String exeName) throws IOException {
        ProcessBuilder pb =
                new ProcessBuilder("gcc", "-std=c++17", sourceCodeFile, "-o", exeName, "-lstdc++");
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
    }

    private File CompileFileWithJava(String sourceCodeFile, String className) throws IOException {
        ProcessBuilder pb =
                new ProcessBuilder("javac", sourceCodeFile);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return new File(className);
    }

    private String RunCppExecutable(String exeName, String input) throws IOException {
        Process p = Runtime.getRuntime().exec(exeName);
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
    private String RunJavaExecutable(String className, String input) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"java", className});
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString().trim();
    }

    private String RunGoExecutable(String fileName, String input) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"go", "run", fileName});
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString().trim();
    }

    private String RunPythonExecutable(String fileName, String input) throws IOException {
        Process p = Runtime.getRuntime().exec(new String[]{"python3", fileName});
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString().trim();
    }

}