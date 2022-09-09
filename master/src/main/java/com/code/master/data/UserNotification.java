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

@Entity(name = "user_notification")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserNotification {
    @Id @NotNull
    @Column(name = "notification_id")
    private String id;
    @NotNull @Column(name = "notification_type")
    private String type;
    @NotNull @Column(name = "seen")
    private boolean seen;
    @NotNull @Column(name = "to_user_id")
    private String toUserId;
    @NotNull @Column(name = "from_user_id")
    private String fromUserId;
    @NotNull @Column(name = "post_id")
    private String postId;
    @Column(name = "comment_text")
    private String commentText;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserNotification() {
        this.id = UUID.randomUUID().toString();
        this.seen = false;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
