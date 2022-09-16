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
import java.time.Instant;
import java.util.UUID;

@Entity(name = "user_group")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class UserGroup {
    @Id @NonNull
    @Column(name = "group_id")
    private String groupId;
    @NonNull
    @Column(name = "group_name")
    private String groupName;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserGroup() {
        this.groupId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
