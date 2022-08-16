package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.common.CreateProfileHTTPRequest;
import com.code.master.common.SubmitCodeHTTPRequest;
import com.code.master.common.UpdateProfileHTTPRequest;
import com.code.master.data.*;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import org.apache.catalina.User;
import org.json.JSONArray;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;

@RestController
public class MasterController {
    public static JsonJacksonFormat jsonFormat = new JsonJacksonFormat();
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
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();
        JSONObject result = new JSONObject();
        JSONArray ar = new JSONArray();
        for (UserProfile profile : userProfiles) {
           ar.put(getUserStats(profile.getUserId()));
        }
        return result.toString();
    }

    private JSONObject getUserStats(String userId) {
        List<UserSubmission> submissions = this.userSubmissionRepository.findByUserId(userId);
        HashSet<Long> dates = new HashSet<>();
        ArrayList<Long> datesList = new ArrayList<>();
        int referralCount = 0;
        int numberUniqueDays = 0;
        int longestStreak = 0;
        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date startDate = parser.parse(Constants.START_DATE);
            // Step-I: Get Number of Unique Submission Days.
            for (int idx = 0; idx < submissions.size(); ++idx) {
                Instant i = submissions.get(idx).getCreatedAt();
                Date endDate = Date.from(Instant.now());
                long diffInMillis = Math.abs(endDate.getTime() - startDate.getTime());
                long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                dates.add(diffInDays);
                datesList.add(diffInDays);
            }
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
        numberUniqueDays = dates.size();

        // Step-II: Get Number of Referrals.
        List<UserProfile> userProfiles = this.userProfileRepository.findByReferrerId(userId);
        for (int idx = 0; idx < userProfiles.size(); ++idx) {
            UserProfile profile = userProfiles.get(idx);
            if (profile.getReferrerId() != profile.getUserId()) ++referralCount;
        }

        // Step-III: Get The Longest Streak.
        Collections.sort(datesList);
        int currRun = 0;
        for (int idx = 0; idx < datesList.size(); ++idx) {
            final long diff = datesList.get(idx + 1) - datesList.get(idx);
            if (diff == 0) continue;
            if (diff == 1) {
                currRun++;
                longestStreak = max(longestStreak, currRun);
            } else {
                currRun = 1;
            }
        }
        return new JSONObject()
                .put("numberUniqueDays", numberUniqueDays)
                .put("referralCount", referralCount)
                .put("longestStreak", longestStreak);
    }

    @GetMapping(path = "/mySubmissions")
    public String handleGetMySubmissions(Principal user) {
        final String userId = user.getName();
        List<UserSubmission> submissions = this.userSubmissionRepository.findByUserId(userId);
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (UserSubmission submission : submissions) {
            JSONObject submissionJSONObject = ToJSONObject(submission);
            arr.put(submissionJSONObject);
        }
        object.put("data", arr);
        return object.toString();
    }

    private JSONObject ToJSONObject(UserSubmission submission) {
        JSONObject object = new JSONObject();
        object.put("problemName", submission.getProblemName())
              .put("problemLink", submission.getProblemLink())
              .put("solutionLink", submission.getSolutionLink());
        return object;
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
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
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
