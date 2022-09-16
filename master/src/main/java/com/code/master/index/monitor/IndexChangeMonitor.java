package com.code.master.index.monitor;

import com.code.master.data.ProblemDescription;
import com.code.master.data.ProblemDescriptionRepository;
import com.code.master.data.UserProfile;
import com.code.master.data.UserProfileRepository;
import com.code.master.index.model.ProblemDocument;
import com.code.master.index.model.UserDocument;
import com.code.master.index.repository.ProblemDocumentRepository;
import com.code.master.index.repository.UserDocumentRepository;

import java.util.List;

public class IndexChangeMonitor implements Runnable {
    private ProblemDocumentRepository problemDocumentRepository;
    private UserDocumentRepository userDocumentRepository;
    private ProblemDescriptionRepository problemDescriptionRepository;
    private UserProfileRepository userProfileRepository;

    private Integer maxRuns;
    private Integer numRuns = 0;
    private long sleepIntervalMS;

    public IndexChangeMonitor(ProblemDocumentRepository problemDocumentRepository,
                              UserDocumentRepository userDocumentRepository,
                              ProblemDescriptionRepository problemDescriptionRepository,
                              UserProfileRepository userProfileRepository,
                              Integer maxRuns,
                              long sleepIntervalMS) {
        this.problemDocumentRepository = problemDocumentRepository;
        this.userDocumentRepository = userDocumentRepository;
        this.problemDescriptionRepository = problemDescriptionRepository;
        this.userProfileRepository = userProfileRepository;
        this.maxRuns = maxRuns;
        this.sleepIntervalMS = sleepIntervalMS;
    }

    @Override
    public void run() {
        while (numRuns < maxRuns) {
            TriggerMonitor();
            ++numRuns;
            try {
                Thread.sleep(this.sleepIntervalMS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void TriggerMonitor() {
        // Step-I: Search all the users and update the index.
        List<ProblemDescription> problems =  this.problemDescriptionRepository.findAll();
        for (ProblemDescription problem : problems) {
            ProblemDocument problemDocument = new ProblemDocument();
            problemDocument.setProblemId(problem.getProblemId());
            problemDocument.setProblemName(problem.getTitle());
            this.problemDocumentRepository.save(problemDocument);
        }

        // Step-II: Search for all the code problems and update the index.
        List<UserProfile> users =  this.userProfileRepository.findAll();
        int idx = 1;
        for (UserProfile user : users) {
            UserDocument userDocument = new UserDocument();
            userDocument.setUserId(user.getUserId());
            if (user.getName() != null) {
                userDocument.setUserId(user.getName());
            } else {
                userDocument.setUserId("Coding Ninja " + idx);
                idx++;
            }
            this.userDocumentRepository.save(userDocument);
        }
    }
}

