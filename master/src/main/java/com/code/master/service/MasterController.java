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
import org.springframework.web.bind.annotation.*;

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
        return GetProfile(user.getName());
    }

    @GetMapping
    public String handleGoogleGetProfile(@RequestParam(value = "userId") String userId) {
        return GetProfile(userId);
    }

    @GetMapping("/google/leaderBoard")
    public String handleGetLeaderBoard(@RequestParam(value = "userId") String userId) {
        return GetLeaderBoard();
    }

    @GetMapping(path = "/leaderBoard")
    public String handleGetLeaderBoard() {
        return GetLeaderBoard();
    }

    @GetMapping(path = "/mySubmissions")
    public String handleGetMySubmissions(Principal user) {
        return GetMySubmissions(user.getName());
    }
    @GetMapping(path = "/google/mySubmissions")
    public String handleGetMySubmissions(@RequestParam(value = "userId") String userId) {
        return GetMySubmissions(userId);
    }

    @GetMapping(path = "/problem")
    public String handleGetProblemOfTheDay(Principal user) { return GetProblemOfTheDay(); }
    @GetMapping(path = "/google/problem")
    public String handleGoogleGetProblemOfTheDay() { return GetProblemOfTheDay(); }

    @PostMapping(path = "/submitCode")
    public String handleCodeSubmission(
            @RequestBody SubmitCodeHTTPRequest request, Principal user) {
        return SubmitCode(request);
    }

    @PostMapping(path = "/google/submitCode")
    public String handleGoogleSubmitCode(@RequestBody SubmitCodeHTTPRequest request) {
        return SubmitCode(request);
    }

    @PostMapping(path = "/createProfile")
    public String handleCreateProfile(
            @RequestBody CreateProfileHTTPRequest request, Principal user) {
        return CreateProfile(user.getName(), request.getEmailId(), request.getUserName(), request.getReferrerId());
    }

    @PostMapping(path = "/google/createProfile")
    public String handleCreateProfile(@RequestBody CreateProfileHTTPRequest request) {
        return CreateProfile(request.getUserId(), request.getEmailId(), request.getUserName(), request.getReferrerId());
    }

    @PostMapping(path = "/updateProfile")
    public String handleUpdateProfile(@RequestBody UpdateProfileHTTPRequest request, Principal user) {
        return UpdateProfile(request);
    }

    @PostMapping(path = "/google/updateProfile")
    public String handleGoogleUpdateProfile(@RequestBody UpdateProfileHTTPRequest request) {
        return UpdateProfile(request);
    }

    private String GetMySubmissions(String userId) {
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
                .put("solutionLink", submission.getSolutionLink())
                .put("submissionDate", submission.getCreatedAt());
        return object;
    }

    private String SubmitCode(SubmitCodeHTTPRequest request) {
        UserSubmission userSubmission = new UserSubmission();
        userSubmission.setUserId(request.getUserId());
        userSubmission.setProblemName(request.getProblemName());
        userSubmission.setProblemLink(request.getProblemLink());
        userSubmission.setSolutionLink(request.getSolutionLink());
        this.userSubmissionRepository.save(userSubmission);
        return new JSONObject().put("message", "Success").toString();
    }

    private String CreateProfile(String userId, String userEmailId, String userName, String referrerId) {
        // Check if the profile already exists in the database.
        UserProfile profile = this.userProfileRepository.getByUserId(userId);
        // Create a new profile if it already does not exist in the database.
        if (profile == null) {
            // If not create the profile.
            UserProfile userProfile = new UserProfile(userId, userEmailId);
            userProfile.setName(userName);
            userProfile.setReferrerId(referrerId);
            this.userProfileRepository.save(userProfile);
        }
        return new JSONObject().put("message", "Success").toString();
    }

    @PostMapping
    private String UpdateProfile(UpdateProfileHTTPRequest request) {
        final String userId = request.getUserId();
        UserProfile oldProfile = this.userProfileRepository.getByUserId(userId);
        System.out.printf("Previous profile of the user: {%s}\n", oldProfile.toString());
        UserProfile userProfile = new UserProfile(userId, request.getEmail());
        if (oldProfile != null) {
            userProfile.setReferrerId(oldProfile.getReferrerId());
        }
        if (!request.getName().isEmpty()) {
            userProfile.setName(request.getName());
        }
        if (!request.getOrg().isEmpty()) {
            userProfile.setOrg(request.getOrg());
        }
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "Success").toString();
    }

    private String GetProblemOfTheDay() {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date startDate = parser.parse(Constants.START_DATE);
            Date endDate = Date.from(Instant.now());
            long diffInMillis = Math.abs(endDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            ProblemDescription problemDescription = this.problemDescriptionRepository.getByIndex(diffInDays);
            if (problemDescription != null) {
                String jsonString = new JSONObject()
                        .put("problemName", problemDescription.getTitle())
                        .put("problemLink", problemDescription.getUrl())
                        .toString();
                return jsonString;
            }
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
        return "{}";
    }

    private JSONObject getUserStats(String userId, String userName) {
        List<UserSubmission> submissions = this.userSubmissionRepository.findByUserId(userId);
        HashSet<Long> datesSet = new HashSet<>();
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
                datesSet.add(diffInDays);
            }
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
        numberUniqueDays = datesSet.size();

        // Step-II: Get Number of Referrals.
        List<UserProfile> userProfiles = this.userProfileRepository.findByReferrerId(userId);
        for (int idx = 0; idx < userProfiles.size(); ++idx) {
            UserProfile profile = userProfiles.get(idx);
            if (!profile.getReferrerId().equalsIgnoreCase(profile.getUserId())) ++referralCount;
        }

        // Step-III: Get The Longest Streak.
        datesList.addAll(datesSet);
        Collections.sort(datesList);

        int currRun = 0;
        for (int idx = 0; idx < (datesList.size() - 1); ++idx) {
            final long diff = datesList.get(idx + 1) - datesList.get(idx);
            if (diff == 0) continue;
            if (diff == 1) {
                currRun++;
                longestStreak = max(longestStreak, currRun);
            } else {
                currRun = 1;
            }
        }
        if (datesList.size() > 0) longestStreak++;
        return new JSONObject()
                .put("name", userName)
                .put("numberUniqueDays", numberUniqueDays)
                .put("referralCount", referralCount)
                .put("longestStreak", longestStreak);
    }

    private String GetProfile(String userId) {
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

    private String GetLeaderBoard() {
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();
        JSONObject result = new JSONObject();
        JSONArray ar = new JSONArray();
        for (UserProfile profile : userProfiles) {
            ar.put(getUserStats(profile.getUserId(), profile.getName()));
        }
        result.put("data", ar);
        return result.toString();
    }
}
