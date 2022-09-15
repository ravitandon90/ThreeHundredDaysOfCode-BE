package com.code.master.index;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchResultWrapper {
    private String id; // Could be user-id or problem-id.
    private String title; // Name of the user or name of the problem
    private String type; // Could be "USER" or "PROBLEM".
}
