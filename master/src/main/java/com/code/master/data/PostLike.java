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

@Entity(name = "post_like")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class PostLike {
    @Id @NotNull
    @Column (name = "like_id")
    private String likeId;
    /**
     * A like event can be both positive and negative.
     * Positive: User liked the post.
     * Negative: User had liked the post, and now doesn't like it anymore.
     */
    private boolean isLike;

    @Column(name = "seen")
    private boolean seen;
    @NotNull
    @Column(name = "post_id")
    private String postId;
    @NotNull
    @Column(name = "author_id")
    private String authorId;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public PostLike() {
        this.likeId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
