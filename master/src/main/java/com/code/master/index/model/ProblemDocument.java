package com.code.master.index.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.Date;

@Document(indexName = "problemdoc")
public class ProblemDocument {
    @Id @Getter @Setter private String problemId;
    @Getter @Setter private String problemName;
    @Getter @Setter private String orgId;
    @Getter @Setter private String problemDescription;

    @Field(type = FieldType.Date)
    @Getter @Setter private Date createdAt;

    @Field(type = FieldType.Date)
    @Getter @Setter private Date updatedAt;

    public ProblemDocument() {
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
    }
}