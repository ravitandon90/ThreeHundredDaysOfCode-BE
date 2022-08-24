package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemInputRepository extends JpaRepository<ProblemInput, String> {
    List<ProblemInput> getByProblemId(String problemId);
}