package com.code.master.data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, String>  {
    List<UserSubmission> findByUserId(String userId);

    List<UserSubmission> findByUserIdAndByCreatedAtGreateThan(String userId, Date currentDate);
}
