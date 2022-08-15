package com.code.master.service;

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
    public String handleGetProblemOfTheDay() {
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
        return new JSONObject().put("message", "OK").toString();
    }

    @PostMapping(path = "/createProfile")
    public String handleCreateProfile(
            @RequestBody CreateProfileHTTPRequest request, Principal user) {
        UserProfile userProfile = new UserProfile(user.getName(), request.getEmailId());
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "OK").toString();
    }

    @PostMapping(path = "/updateProfile")
    public String handleUpdateProfile(
            @RequestBody UpdateProfileHTTPRequest request,
            Principal user) {
        UserProfile userProfile = new UserProfile(user.getName(), request.getEmail());
        if (!request.getOrg().isEmpty()) {
            userProfile.setOrg(request.getOrg());
        }
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "OK").toString();
    }
}
