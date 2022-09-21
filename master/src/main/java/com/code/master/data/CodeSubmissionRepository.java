package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeSubmissionRepository extends JpaRepository<CodeSubmission, String> {
    CodeSubmission getBySubmissionId(String submissionId);
    List<CodeSubmission> getByProblemId(String problemId);
    List<CodeSubmission> getByProblemIdAndGroupId(String problemId, String groupId);
    List<CodeSubmission> findByUserId(String userId);
}
