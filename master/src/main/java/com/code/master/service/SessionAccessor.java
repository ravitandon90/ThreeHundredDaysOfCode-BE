package com.code.master.service;

import com.code.master.data.UserProfileRepository;
import com.code.master.data.UserSession;
import com.code.master.data.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionAccessor {
    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public String createSession(String userId, String groupId, String sessionType,
                                String problemId, String language, String solutionCode) {
        UserSession userSession = new UserSession();
        userSession.setSessionType(sessionType);
        userSession.setGroupId(groupId);
        userSession.setUserId(userId);
        userSession.setGroupId(groupId);
        userSession.setProblemId(problemId);
        userSession.setSolutionLanguage(language);
        userSession.setSolutionCode(solutionCode);
        this.userSessionRepository.save(userSession);
        return userSession.getSessionId();
    }

    public UserSession getSessionFromProblem(String userId, String problemId) {
        return this.userSessionRepository.findFirstByUserIdAndProblemIdOrderByCreatedAtDesc(userId, problemId);
    }

    public UserSession getSessionFromId(String sessionId) {
        UserSession userSession = this.userSessionRepository.getBySessionId(sessionId);
        return userSession;
    }

    public String updateSession(
            String sessionId,
            String userId,
            String groupId,
            String sessionType,
            String problemId,
            String language,
            String solutionCode) {
        UserSession userSession = this.userSessionRepository.getById(sessionId);
        if (userSession == null) {
            return createSession(userId, groupId, sessionType, problemId, language, solutionCode);
        }
        userSession = new UserSession();
        userSession.setSessionId(sessionId);
        userSession.setSessionType(sessionType);
        userSession.setGroupId(groupId);
        userSession.setUserId(userId);
        userSession.setGroupId(groupId);
        userSession.setProblemId(problemId);
        userSession.setSolutionLanguage(language);
        userSession.setSolutionCode(solutionCode);
        this.userSessionRepository.save(userSession);
        return userSession.getSessionId();
    }
}
