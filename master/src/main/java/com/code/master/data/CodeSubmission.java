package com.code.master.data;

import lombok.AllArgsConstructor;
import lombok.Data;
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

@Entity(name = "code_submission")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class CodeSubmission {
    @Id
    @NotNull
    @Column(name = "submission_id")
    private String submissionId;
    @NotNull
    @Column(name = "user_id")
    private String userId;
    @NotNull
    @Column(name = "problem_id")
    private String problemId;
    @NotNull
    @Column(name = "solution_code")
    private String solutionCode;
    @NotNull
    @Column(name = "language")
    private String language;
    @Column(name = "accepted")
    private boolean accepted;
    @Column(name = "running_time_ms")
    private long runningTimeMS;
    @Column(name = "memory_consumed")
    private long memoryConsumption;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public CodeSubmission() {
        this.submissionId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
