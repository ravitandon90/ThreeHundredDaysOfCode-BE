package com.code.master.data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class LeetcodeProblemDefinition {
    @NotNull
    @Column(name = "title_slug")
    private String titleSlug;
    @NotNull
    @Column(name = "content")
    private String content;

    public LeetcodeProblemDefinition(String titleSlug, String content) {
        this.titleSlug = titleSlug;
        this.content = content;
    }
}
