package com.code.master.service;

import com.code.master.data.UserProfileRepository;
import com.code.master.data.UserSession;
import com.code.master.data.UserSessionRepository;

public class SessionAccessor {


    private UserSessionRepository userSessionRepository;
    private UserProfileRepository userProfileRepository;


    public String createSession(String userId, String groupId, String sessionType,
                                String problemId, String problemLink, String language, String solutionCode) {
        UserSession userSession = new UserSession();
        userSession.setSessionType(sessionType);
        userSession.setGroupId(groupId);
        userSession.setUserId(userId);
        userSession.setGroupId(groupId);
        userSession.setProblemId(problemId);
        userSession.setProblemLink(problemLink);
        userSession.setSolutionLanguage(language);
        userSession.setSolutionCode(solutionCode);
        this.userSessionRepository.save(userSession);
        return userSession.getSessionId();
    }

    public UserSession getSessionFromProblem(String userId, String problemId) {
        UserSession userSession = this.userSessionRepository.getByProblemId(problemId);
        return userSession;
    }

    public String updateSession(
            String sessionId, String userId, String groupId, String sessionType,
                                String problemId, String problemLink, String language, String solutionCode) {

        UserSession userSession = this.userSessionRepository.getById(sessionId);
        if (userSession == null) {
            return createSession(userId, groupId, sessionType, problemId, problemLink, language, solutionCode);
        }
        userSession = new UserSession();
        userSession.setSessionId(sessionId);
        userSession.setSessionType(sessionType);
        userSession.setGroupId(groupId);
        userSession.setUserId(userId);
        userSession.setGroupId(groupId);
        userSession.setProblemId(problemId);
        userSession.setProblemLink(problemLink);
        userSession.setSolutionLanguage(language);
        userSession.setSolutionCode(solutionCode);
        this.userSessionRepository.save(userSession);
        return userSession.getSessionId();
    }
}
