package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemDescriptionRepository extends JpaRepository<ProblemDescription, String> {
    ProblemDescription getByIndex(long index);
    List<ProblemDescription> findAll();

    ProblemDescription getByProblemId(String problemId);
}



