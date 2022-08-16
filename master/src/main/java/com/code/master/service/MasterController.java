package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.common.CreateProfileHTTPRequest;
import com.code.master.common.SubmitCodeHTTPRequest;
import com.code.master.common.UpdateProfileHTTPRequest;
import com.code.master.data.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class MasterController {

    @Autowired
    private ProblemDescriptionRepository problemDescriptionRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserSubmissionRepository userSubmissionRepository;

    @GetMapping(path = "/")
    public String handlePing() { return "Master-Ok"; }

    @GetMapping(path = "/me")
    public String handleGetProfile(Principal user) {
        final String userId = user.getName();
        // Step-I: Get data from the database.
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);
        if (userProfile == null) return "{}";

        // Step-II: Translate this into a JSON & send response back to the client.
        String jsonString = new JSONObject()
                .put("name", userProfile.getName())
                .put("email", userProfile.getEmailId())
                .put("org", userProfile.getOrg())
                .toString();
        return jsonString;
    }

    @GetMapping(path = "/leaderboard")
    public String handleGetLeaderBoard() {
        return "{}";
    }

    @GetMapping(path = "/problem")
    public String handleGetProblemOfTheDay(Principal user) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date startDate = parser.parse(Constants.START_DATE);
            Date endDate = Date.from(Instant.now());
            long diffInMillis = Math.abs(endDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            ProblemDescription problemDescription = this.problemDescriptionRepository.getByIndex(diffInDays);
            if (problemDescription != null) {
                String jsonString = new JSONObject()
                        .put("title", problemDescription.getTitle())
                        .put("id", problemDescription.getIndex())
                        .put("complexity", problemDescription.getComplexity())
                        .put("url", problemDescription.getDescription())
                        .put("description", problemDescription.getDescription())
                        .put("example", problemDescription.getExample())
                        .put("constraints", problemDescription.getConstraints())
                        .toString();
                return jsonString;
            }
        } catch (ParseException e) {}
        return "{}";
    }

    @PostMapping(path = "/submitCode")
    public String handleCodeSubmission(
            @RequestBody SubmitCodeHTTPRequest request, Principal user) {
        final String userId = user.getName();
        UserSubmission userSubmission = new UserSubmission();
        userSubmission.setUserId(userId);
        userSubmission.setProblemLink(request.getProblemLink());
        userSubmission.setSolutionLink(request.getSolutionLink());
        this.userSubmissionRepository.save(userSubmission);
        return new JSONObject().put("message", "Success").toString();
    }

    @PostMapping(path = "/createProfile")
    public String handleCreateProfile(
            @RequestBody CreateProfileHTTPRequest request, Principal user) {
        UserProfile userProfile = new UserProfile(user.getName(), request.getEmailId());
        userProfile.setReferrerId(request.getReferrerId());
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "Success").toString();
    }

    @PostMapping(path = "/updateProfile")
    public String handleUpdateProfile(
            @RequestBody UpdateProfileHTTPRequest request,
            Principal user) {
        UserProfile userProfile = new UserProfile(user.getName(), request.getEmail());
        if (!request.getName().isEmpty()) {
            userProfile.setName(request.getName());
        }
        if (!request.getOrg().isEmpty()) {
            userProfile.setOrg(request.getOrg());
        }
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "Success").toString();
    }
}
