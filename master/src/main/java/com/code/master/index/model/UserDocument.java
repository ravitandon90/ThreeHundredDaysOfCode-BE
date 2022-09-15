package com.code.master.index.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.Date;

@Document(indexName = "userdoc")
public class UserDocument {
    @Id @Getter @Setter private String userId;
    @Getter @Setter private String userName;
    @Getter @Setter private String orgId;

    @Field(type = FieldType.Date)
    @Getter @Setter private Date createdAt;

    @Field(type = FieldType.Date)
    @Getter @Setter private Date updatedAt;

    public UserDocument() {
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
    }

}