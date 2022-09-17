package com.code.master.common;

import com.code.master.data.CodeSubmission;
import com.code.master.data.UserSubmission;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.Instant;

@Getter @Setter
public class SubmissionWrapper {
    private UserSubmission userSubmission;
    private CodeSubmission codeSubmission;

    public void setUserSubmission(UserSubmission userSubmission) {
        this.userSubmission = userSubmission;
        this.createdAt = userSubmission.getCreatedAt();
        this.userId = userSubmission.getUserId();
        this.setType(SubmissionWrapperType.GITHUB_SUBMISSION);
    }

    public void setCodeSubmission(CodeSubmission codeSubmission) {
        this.codeSubmission = codeSubmission;
        this.createdAt = codeSubmission.getCreatedAt();
        this.userId = codeSubmission.getUserId();
        this.setType(SubmissionWrapperType.CODE_SUBMISSION);
    }

    public String getProblemId() {
        if (this.type == SubmissionWrapperType.GITHUB_SUBMISSION) return "";
        return codeSubmission.getProblemId();
    }

    public String getProblemUrl() {
        if (this.type == SubmissionWrapperType.GITHUB_SUBMISSION) return userSubmission.getProblemLink();
        return "";
    }

   public String getSolutionLink() {
        if (this.type == SubmissionWrapperType.GITHUB_SUBMISSION) return userSubmission.getSolutionLink();
        return "";
   }

   public String getSubmissionId() {
       if (this.type == SubmissionWrapperType.CODE_SUBMISSION) return codeSubmission.getSubmissionId();
       return "";
   }

    private SubmissionWrapperType type;
    private Instant createdAt;
    private String userId;
}
