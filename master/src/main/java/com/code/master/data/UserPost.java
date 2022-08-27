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

@Entity(name = "user_post")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserPost {

    @Id @NotNull
    @Column(name = "post_id")
    private String postId;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "text")
    private String text;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "video_url")
    private String video_url;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserPost() {
        this.postId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
