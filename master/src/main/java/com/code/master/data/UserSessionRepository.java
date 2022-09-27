package com.code.master.data;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    UserSession getByProblemId(String problemId);
    UserSession getByUserIdAndProblemId(String userId, String problemId);
    UserSession getBySessionUrl(String sessionUrl);

}

