package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.data.ProblemDescriptionRepository;
import com.code.master.data.UserProfileRepository;
import com.code.master.index.monitor.IndexChangeMonitor;
import com.code.master.index.repository.ProblemDocumentRepository;
import com.code.master.index.repository.UserDocumentRepository;

import javax.annotation.PostConstruct;

public class IndexerService {
    private ProblemDocumentRepository problemDocumentRepository;
    private UserDocumentRepository userDocumentRepository;
    private UserProfileRepository userProfileRepository;
    private ProblemDescriptionRepository problemDescriptionRepository;

    public IndexerService(
             ProblemDocumentRepository problemDocumentRepository,
             UserDocumentRepository userDocumentRepository,
             UserProfileRepository userProfileRepository,
             ProblemDescriptionRepository problemDescriptionRepository) {
        this.problemDocumentRepository = problemDocumentRepository;
        this.userDocumentRepository  = userDocumentRepository;
        this.userProfileRepository = userProfileRepository;
        this.problemDescriptionRepository = problemDescriptionRepository;
    }

    @PostConstruct
    private void BuildIndex() {
        if (!Constants.RUN_CHANGE_MONITORS) return;

        Runnable indexChangeMonitorThread =
                new IndexChangeMonitor(
                        this.problemDocumentRepository,
                        this.userDocumentRepository,
                        this.problemDescriptionRepository,
                        this.userProfileRepository,
                        Integer.MAX_VALUE,
                        Constants.GSUITE_POLL_INTERVAL_MINUTES * 60 * 1000);
        new Thread(indexChangeMonitorThread).start();
    }
}