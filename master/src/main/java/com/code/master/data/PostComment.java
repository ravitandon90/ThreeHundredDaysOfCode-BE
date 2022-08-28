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

@Entity(name = "post_comment")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class PostComment {
    @Id
    @NotNull
    @Column (name = "comment_id")
    private String commentId;

    @NotNull
    @Column(name = "post_id")
    private String postId;

    @NotNull
    @Column(name = "author_id")
    private String authorId;

    @NotNull
    @Column(name = "text")
    private String text;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public PostComment() {
        this.commentId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
