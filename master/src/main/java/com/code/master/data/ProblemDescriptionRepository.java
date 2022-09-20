package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemDescriptionRepository extends JpaRepository<ProblemDescription, String> {
    ProblemDescription getByIndex(long index);
    ProblemDescription getByIndexAndGroupId(long index, String groupId);
    List<ProblemDescription> findAll();
    List<ProblemDescription> findAllByGroupId(String groupId);
    ProblemDescription getByProblemId(String problemId);
}



