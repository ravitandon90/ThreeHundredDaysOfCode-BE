package com.code.master.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitCodeSolutionHTTPRequest {
    private String user_id;
    private String command_line_arguments;
    private String compiler_options;
    private int language_id;
    private boolean redirect_stderr_to_stdout;
    private String source_code;
    private String stdin;
    private String problem_id;
}
