package com.code.master.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "problem_description")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDescription {
    @Id @NotNull
    @Column(name = "problem_id")
    private String problemId;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "url")
    private String url;
    @NotNull
    @Column(name = "complexity")
    private String complexity;
    @NotNull
    @Column(name = "description")
    private String description;
    @Column(name = "example")
    private String example;
    @Column(name = "problem_constraints")
    private String constraints;
    @Column(name = "slug_title")
    private String slugTitle;
    @NotNull
    @Column(name = "idx")
    private long index;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public ProblemDescription(String url, String description) {
        this.problemId = UUID.randomUUID().toString();
        this.url = url;
        this.description = description;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}