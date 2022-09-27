package com.code.master.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
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

@Entity(name = "user_session")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserSession {
    @Id @NotNull @Column(name = "session_id")
    private String sessionId;
    @NotNull @Column(name = "session_type")
    private String sessionType;
    @Column(name = "problem_id")
    private String problemId;
    @Column(name = "solution_link")
    private String solutionLink;
    @Column(name = "solution_language")
    private String solutionLanguage;
    @Column(name = "solution_code")
    private String solutionCode;
    @NotNull @Column(name = "user_id")
    private String userId;
    @NotNull @Column(name = "group_id")
    private String groupId;
    @CreatedDate @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate @Column(name = "updated_at")
    private Instant updatedAt;

    public UserSession() {
        this.sessionId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}

