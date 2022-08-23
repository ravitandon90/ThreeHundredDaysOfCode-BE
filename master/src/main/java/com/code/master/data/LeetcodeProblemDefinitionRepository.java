package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeetcodeProblemDefinitionRepository extends JpaRepository<LeetcodeProblemDefinition, String> {
    LeetcodeProblemDefinition getByTitle(String title);
}
