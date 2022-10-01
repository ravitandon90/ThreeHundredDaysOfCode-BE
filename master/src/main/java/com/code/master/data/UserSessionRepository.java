package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    List<UserSession> getByUserIdAndProblemId(String userId, String problemId);
    UserSession getBySessionId(String sessionId);
    UserSession findFirstByUserIdAndProblemIdOrderByCreatedAtDesc(String userId, String problemId);
}

