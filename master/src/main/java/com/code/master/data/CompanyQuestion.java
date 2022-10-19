package com.code.master.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;


@Entity(name = "company_activity")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
public class CompanyQuestion {


    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "problem_id")
    private String problem;

    @NotNull
    @Column(name = "companyName")
    private String companyName;

    @CreatedDate
    @Column(name = "posted_at")
    private Instant postedAt;

    @NotNull
    @Column(name = "posted_by")
    private String postedBy;



    public CompanyQuestion() {
        this.id = UUID.randomUUID().toString();
        this.postedAt = Instant.now();
    }

}
