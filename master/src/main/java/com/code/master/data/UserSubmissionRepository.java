package com.code.master.data;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, String>  {
    List<UserSubmission> findByUserId(String userId);
    List<UserSubmission> findAll();
    List<UserSubmission> findByGroupId(String groupId);

    List<UserSubmission> findByUserIdAndCreatedAtGreaterThan(String userId, Instant startDate);

    List<UserSubmission> findByGroupIdAndCreatedAtGreaterThan(String groupId, Instant startDate);

    List<UserSubmission> findByProblemName(String problemName);
    List<UserSubmission> findByProblemNameAndGroupId(String problemName, String groupId);
}
