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

@Entity(name = "user_follower")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserFollower {
    @Id @NotNull
    @Column(name = "user_follower_id")
    private String id;
    @NotNull
    @Column(name = "follower_id")
    private String follower_id;
    @NotNull
    @Column(name = "followee_id")
    private String followee_id;
    @Column(name = "follower_type")
    private String followerType;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
    public UserFollower() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
