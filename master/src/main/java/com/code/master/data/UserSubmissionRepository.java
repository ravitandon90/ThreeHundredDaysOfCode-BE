package com.code.master.data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, String>  {
    List<UserSubmission> findByUserId(String userId);

    List<UserSubmission> findByUserIdAndCreatedAtGreaterThan(String userId, Instant currentDate);
}
