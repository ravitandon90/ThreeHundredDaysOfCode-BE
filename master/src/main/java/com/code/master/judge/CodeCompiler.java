package com.code.master.judge;

import com.code.master.common.Constants;
import jodd.io.StreamGobbler;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.Random;

public class CodeCompiler {
    public JSONObject run(String sourceCode, String input, int selectedLanguage, String className) {
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
            try {
                final String exeName = "Solution";
                // Step-II: Compile the program.
                final String compilerOutput = CompileFileWithCpp(String.valueOf(sourceCodeFile), exeName);
                if (!compilerOutput.isEmpty()) {
                    if (sourceCodeFile != null) sourceCodeFile.delete();
                    return new JSONObject().put("message", "Error").put("output", compilerOutput);
                }
                // Step-III: Execute the program.
                try {
                    final String cmd = "./" + exeName;
                    File f = new File(exeName);
                    f.deleteOnExit();
                    return RunExecutable(input, new String[]{cmd});
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

    private JSONObject runCodeJava(String sourceCodeIn, String input, String classNameIn) {
        String output = "";
        String message = "Success";
        final String className = classNameIn + RandomStringUtils.randomAlphabetic (4);
        final String sourceCode = sourceCodeIn.replace("public class " + classNameIn, "public class " + className);
        try {
            // Step-I: Copy the source code to a temporary file.
            File sourceCodeFile = CreateFile(sourceCode, className, ".java");
            sourceCodeFile.deleteOnExit();
            // Step-II: Compile the file to generate the Main class.
            String compilerError = CompileFileWithJava(String.valueOf(sourceCodeFile));
            output = compilerError;
            File classFile = new File(className);
            classFile.deleteOnExit();
            // Step-III: Run the executable.
            if (output.isEmpty()) {
                return RunExecutable(input, new String[]{"java", classFile.getName()});
            } else {
                message = "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject().put("message", message).put("output", output);
    }

    private JSONObject runCodePython(String sourceCode, String input) {
        String output = "";

        File sourceCodeFile = null;
        try {
            // Step-I: Copy the source code to a temporary file.
            sourceCodeFile = CreateFile(sourceCode, "Main", ".py");
            System.out.println(sourceCodeFile);
            try {
                // Step-III: Run the executable
                return RunExecutable(input, new String[]{"python3", sourceCodeFile.getName()});
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Step-IV: Clean up all the generated files.
        finally {
            if (sourceCodeFile != null) sourceCodeFile.delete();
        }
        return new JSONObject().put("message", "Success").put("output", output);
    }

    private JSONObject runCodeGo(String sourceCode, String input) {
        final String className = RandomStringUtils.randomAlphabetic(8);
        try {
            // Step-I: Copy the source code to a temporary file.
            File sourceCodeFile = CreateFile(sourceCode, className, ".go");
            sourceCodeFile.deleteOnExit();
            System.out.println(sourceCodeFile);
            // Step-III: Run the executable
            return RunExecutable(input, new String[]{"go", "run", sourceCodeFile.getName()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject().put("message", "Error").put("output", "");
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

    private String CompileFileWithJava(String sourceCodeFile) throws IOException {
        ProcessBuilder pb =
                new ProcessBuilder("javac", sourceCodeFile);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
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
        outputFile.deleteOnExit();
        errorFile.deleteOnExit();
        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), eStream);
        StreamGobbler inputGobbler = new StreamGobbler(p.getInputStream(), oStream);
        errorGobbler.run();
        inputGobbler.run();
        int exitCode = p.waitFor();
        oStream.flush();
        eStream.flush();
        if (exitCode == 0) {
            output = ReadFile(outputFile.getPath()).trim();
        } else if (exitCode == 139) {
            output = "Segmentation Fault.";
        } else {
            output = ReadFile(errorFile.getPath()).trim();
        }
        return output;
    }

    private JSONObject RunExecutable(String input, String[] cmdArray) throws IOException, InterruptedException {
        JSONObject jsonObject = new JSONObject();
        String output = "";
        Process p = Runtime.getRuntime().exec(cmdArray);
        OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream());
        writer.write(input);
        writer.close();
        File outputFile = new File("/tmp/output.txt");
        File errorFile = new File("/tmp/error.txt");
        PrintStream oStream = new PrintStream(outputFile);
        PrintStream eStream = new PrintStream(errorFile);
        outputFile.deleteOnExit();
        errorFile.deleteOnExit();
        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), eStream);
        StreamGobbler inputGobbler = new StreamGobbler(p.getInputStream(), oStream);
        errorGobbler.run();
        inputGobbler.run();
        int exitCode = p.waitFor();
        oStream.flush();
        eStream.flush();
        if (exitCode == 0) {
            output = ReadFile(outputFile.getPath()).trim();

        } else if (exitCode == 139) { // Specific to Cpp for now.
            output = "Segmentation Fault.";
        } else {
            output = ReadFile(errorFile.getPath()).trim();
        }
        final String message = (exitCode == 0) ? "Success" : "Error";
        jsonObject.put("message", message).put("output", output);
        return jsonObject;
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