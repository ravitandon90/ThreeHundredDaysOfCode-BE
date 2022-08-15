package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemDescriptionRepository extends JpaRepository<ProblemDescription, String> {
    ProblemDescription getByIndex(long index);

}



