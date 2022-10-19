package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyQuestionareRepository extends JpaRepository<CompanyQuestion, String> {
    List<CompanyQuestion> findByProblem(String problem);
    List<CompanyQuestion> findByCompanyName(String companyName);

}
