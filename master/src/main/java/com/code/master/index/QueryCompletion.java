package com.code.master.index;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class QueryCompletion {
    private String displayText;
    private double score;
    private long rank;
    private String source;
    private String queryCompletionType;
}
