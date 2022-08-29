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

@Entity(name = "problem_base_code")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class ProblemBaseCode {
    @Id
    @NotNull
    @Column(name = "id")
    private String id;
    @NotNull
    @Column(name = "problem_id")
    private String problemId;

    @NotNull
    @Column(name = "language")
    private String language;

    @NotNull
    @Column(name = "base_code")
    private String baseCode;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public ProblemBaseCode() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
