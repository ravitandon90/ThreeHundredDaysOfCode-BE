package com.code.master.judge;

import com.code.master.data.ProblemDescription;
import com.code.master.data.ProblemInput;
import com.code.master.data.ProblemInputRepository;
import com.code.master.utils.Utils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CodeJudge {
    private CodeCompiler compiler;
    private ProblemInputRepository problemInputRepository;

    // Constructor.
    public CodeJudge(ProblemInputRepository problemInputRepository) {
        this.compiler = new CodeCompiler();
        this.problemInputRepository = problemInputRepository;
    }

    // Runs the code and sends the result to the client.
    public JSONObject run(String sourceCode, String problemId, String mode, String userInput, int selectedLanguage) {
        JSONObject result = new JSONObject();
        String message = "";
        final String sourceCodeDecoded = Utils.Decode(sourceCode);
        // Step-I: Find the problem from the database.
        // Step-II: Validate the codebase. Two cases: User-defined input, pre-defined input.
        if (mode.equalsIgnoreCase("judge")) {
            List<ProblemInput> inputs = this.problemInputRepository.getByProblemId(problemId);
            for (ProblemInput input : inputs) {
                final String inputDecoded = Utils.Decode(input.getArgument());
                // Pre-defined input.
                // Step-II(a): Run the input code to see if the result passes on pre-defined inputs.
                JSONObject response = compiler.run(sourceCodeDecoded, inputDecoded, selectedLanguage, "Main");
                // If response is success, continue.
                if (Utils.IsSuccess(response)) {
                    final String output = Utils.GetOutput(response);
                    if (!output.equalsIgnoreCase(input.getOutput())) {
                        // Return failure.
                        // Failure should have expected output and received output.
                    }
                }
            }
        } else if (mode.equalsIgnoreCase("evaluate")) {
            JSONObject response = compiler.run(sourceCode, userInput, selectedLanguage, "Main");
            message = response.toString();
        }
        return result.put("message", message);
    }
}
