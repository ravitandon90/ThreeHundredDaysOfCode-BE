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

@Entity(name = "user_profile")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @NotNull @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "org")
    private String org;
    @NotNull
    @Column(name = "referrer_id")
    private String referrerId;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserProfile(String userId, String emailId) {
        this.userId = userId;
        this.emailId = emailId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}

