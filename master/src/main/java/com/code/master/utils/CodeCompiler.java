package com.code.master.utils;

import com.code.master.common.Constants;
import jodd.io.StreamGobbler;
import org.json.JSONObject;

import java.io.*;

public class CodeCompiler {
    public JSONObject run(String sourceCode, String input, int selectedLanguage, String className)  {
        JSONObject result = null;
        try {
            switch (selectedLanguage) {
                case (Constants.LANGUAGE_CPP_CODE):
                    result = runCodeCpp(sourceCode, input);
                    break;

                case (Constants.LANGUAGE_GO_CODE):
                    result = runCodeGo(sourceCode, input);
                    break;

                case (Constants.LANGUAGE_PYTHON_CODE):
                    result = runCodePython(sourceCode, input);
                    break;

                case (Constants.LANGUAGE_JAVA_CODE):
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
            File sourceCodeFile = CreateFile(sourceCode, "Solution", ".cpp");
            sourceCodeFile.deleteOnExit();
            System.out.printf("Source code file: {%s}\n", sourceCodeFile);
            try {
                final String exeName = "Solution";
                // Step-II: Compile the program.
                final String compilerOutput = CompileFileWithCpp(String.valueOf(sourceCodeFile), exeName);
                if (!compilerOutput.isEmpty()) {
                    return new JSONObject().put("message", "Error").put("output", compilerOutput);
                }
                System.out.printf("Compiler Output: {%s}\n", compilerOutput);
                // Step-III: Execute the program.
                try {
                    output = RunCppExecutable(exeName, input);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("RunCppExecutable: {%s}\n", output);
            } catch (IOException e) {
                System.out.println(e);
            }
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
                String compileOutput = CompileFileWithJava(String.valueOf(sourceCodeFile), className);
                System.out.printf("Compiler-Output: {%s}\n", compileOutput);
                classFile = new File(className);
                // Step-III: Run the executable
                output = RunJavaExecutable(className, input);
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
        File tempFile = new File(className + suffix);
        FileWriter fileWriter = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(sourceCode);
        bw.close();
        return tempFile;
    }

    private String CompileFileWithCpp(String sourceCodeFile, String exeName) throws IOException {
        File outputFile = new File("/tmp/error.txt");
        outputFile.deleteOnExit();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(outputFile));
        ProcessBuilder pb =
                new ProcessBuilder("gcc", "-std=c++17", sourceCodeFile, "-o", exeName, "-lstdc++");
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        System.setOut(stdout);
        return ReadFile(outputFile.getPath());
    }

    private String ReadFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }

    private String CompileFileWithJava(String sourceCodeFile, String className) throws IOException {
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
        return builder.toString();
    }
    private String RunCppExecutable(String exeName, String input) throws IOException, InterruptedException {
        String output = "";
        final String cmd = "./" + exeName;
        Process p = Runtime.getRuntime().exec(new String[] {cmd});
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        File outputFile = new File("/tmp/output.txt");
        File errorFile = new File("/tmp/error.txt");
        PrintStream oStream = new PrintStream(outputFile);
        PrintStream eStream = new PrintStream(errorFile);
        //outputFile.deleteOnExit();
        //errorFile.deleteOnExit();
        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), eStream);
        StreamGobbler inputGobbler = new StreamGobbler(p.getInputStream(), oStream);
        errorGobbler.run();
        inputGobbler.run();
        p.waitFor();
        oStream.flush();
        eStream.flush();
        new PrintStream(errorFile).flush();
        System.out.printf("Error: {%s}\n", ReadFile(errorFile.getPath()));
        System.out.printf("Output: {%s}\n", ReadFile(outputFile.getPath()));
        return output;
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