package com.code.master.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.common.UUIDs;
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


@Entity(name = "user_submission")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserSubmission {
    @Id @NotNull
    @Column(name = "submission_id")
    private String submissionId;
    @NotNull
    @Column(name = "user_id")
    private String userId;
    @NotNull
    @Column(name = "problem_link")
    @NotNull
    private String problemLink;
    @Column(name = "solution_link")
    private String solutionLink;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserSubmission() {
        this.submissionId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}