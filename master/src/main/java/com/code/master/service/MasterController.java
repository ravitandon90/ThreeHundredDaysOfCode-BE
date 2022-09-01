package com.code.master.service;

import com.code.master.common.*;
import com.code.master.data.*;
import com.code.master.judge.CodeJudge;
import com.code.master.utils.Utils;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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
    @Autowired
    private ProblemBaseCodeRepository problemBaseCodeRepository;
    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;
    @Autowired
    private NotificationSchedulerService emailSenderService;
    @Autowired
    private ProblemInputRepository problemInputRepository;
    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    /*********************************** End Of API Definitions. *****************************************/
    @GetMapping(path = "/")
    public String handlePing() {return "Master-Ok"; }

    @GetMapping(path = "/me")
    public String handleGetProfile(Principal user) {
        return GetProfile(user.getName());
    }

    @GetMapping(path = "/google/me")
    public String handleGoogleGetProfile(@RequestParam(value = "userId") String userId) {
        return GetProfile(userId);
    }

    @GetMapping("/google/leaderBoard")
    public String handleGetLeaderBoard(@RequestParam(value = "userId") String userId, @RequestParam(value = "timeFilter") String timeFilter) {
        return getLeaderBoard(timeFilter);
    }

    @GetMapping(path = "/leaderBoard")
    public String handleGetLeaderBoard(@RequestParam(value = "timeFilter") String timeFilter) {
        return getLeaderBoard(timeFilter);
    }

    @GetMapping(path = "/mySubmissions")
    public String handleGetMySubmissions(Principal user) {
        return GetMySubmissions(user.getName());
    }
    @GetMapping(path = "/google/mySubmissions")
    public String handleGetMySubmissions(@RequestParam(value = "userId") String userId) {
        return GetMySubmissions(userId);
    }

    @GetMapping(path = "/problems")
    public String handleGetProblems(Principal user) {
        return GetProblems();
    }

    @GetMapping(path = "/google/problems")
    public String handleGoogleGetProblems() {
        return GetProblems();
    }

    @GetMapping(path = "/problem")
    public String handleGetProblemOfTheDay(Principal user, @RequestParam(value = "logic") String logic) { return GetProblemOfTheDay(logic); }

    @GetMapping(path = "/google/problem")
    public String handleGoogleGetProblemOfTheDay(@RequestParam(value = "logic") String logic) { return GetProblemOfTheDay(logic); }

    @GetMapping(path = "/problemById")
    public String handleGetProblemById(Principal user, @RequestParam(value = "problemId") String problemId) { return GetProblemById(problemId); }

    @GetMapping(path = "/google/problemById")
    public String handleGoogleGetProblemById(@RequestParam(value = "problemId") String problemId) { return GetProblemById(problemId); }

    @GetMapping(path = "/problemBaseCode")
    public String handleGetProblemBaseCode(@RequestParam(value = "languageId") String languageId,@RequestParam(value = "problemId") String problemId) {
        return GetProblemBaseCode(problemId, languageId);
    }

    @GetMapping(path = "/google/problemBaseCode")
    public String handleGoogleGetPorblemBaseCode(@RequestParam(value = "languageId") String languageId, @RequestParam(value = "problemId") String problemId) {
        return GetProblemBaseCode(problemId, languageId);
    }

    @PostMapping(path = "/submitCode")
    public String handleCodeSubmission(
            @RequestBody SubmitCodeHTTPRequest request, Principal user) { return SubmitCode(request); }


    @PostMapping(path = "/submitCodeSolution")
    public String handleSubmitCodeSolution(
            @RequestBody SubmitCodeSolutionHTTPRequest request) { return SubmitCodeSolution(request); }

    @PostMapping(path = "/google/submitCodeSolution")
    public String handleGoogleSubmitCodeSolution(@RequestBody SubmitCodeSolutionHTTPRequest request) {return SubmitCodeSolution(request);}

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
    @PostMapping(path = "/submissions")
    public String handleRunCode(@RequestBody RunCodeHTTPRequest request) {
        return runCode(request);
    }
    @PostMapping(path = "/google/submissions")
    public String handleGoogleRunCode(@RequestBody RunCodeHTTPRequest request) {
        return runCode(request);
    }

    @GetMapping(path = "/google/feed")
    public String handleGoogleGetFeed(@RequestParam(value = "userId") String userId,
                                      @RequestParam(value = "pageId") String pageId) {
        return getFeed(userId, pageId);
    }

    @GetMapping(path = "/feed")
    public String handleGetFeed(Principal user,
                                @RequestParam(value = "pageId") String pageId) {
        return getFeed(user.getName(), pageId);
    }

    @PostMapping(path = "/google/comment")
    public String handleGoogleAddComment(@RequestBody AddCommentHTTPRequest request) {
        return addComment(request);
    }

    @PostMapping(path = "/comment")
    public String handleAddComment(@RequestBody AddCommentHTTPRequest request) {
        return addComment(request);
    }

    @PostMapping(path = "/google/like")
    public String handleGoogleAddLike(@RequestBody AddLikeHTTPRequest request) {
        return addLike(request);
    }

    @PostMapping(path = "/like")
    public String handleAddLike(@RequestBody AddLikeHTTPRequest request) {
        return addLike(request);
    }

    /*********************************** End Of API Definitions. *****************************************/

    private String getFeed(String userId, String pageId) {
        int pageIntId = Integer.parseInt(pageId);
        int startIdx = (pageIntId - 1) * Constants.FEED_PAGE_SIZE;
        int endIdx = startIdx + Constants.FEED_PAGE_SIZE;

        // Step-I: Get all the user posts.
        List<UserPost> userPosts = this.userPostRepository.findAll();

        // Step-II: Find the user posts for the current page.
        userPosts = userPosts.subList(startIdx, endIdx);

        // Step-III: Format the response.
        JSONArray jsonArray = new JSONArray();
        for (UserPost post : userPosts) {
            JSONObject obj = new JSONObject();
            List<PostLike> postLikes = this.postLikeRepository.findAllByPostId(post.getPostId());
            List<PostComment> postComments = this.postCommentRepository.findAllByPostId(post.getPostId());
            JSONArray commentsArr = new JSONArray();
            for (PostComment postComment : postComments) {
                JSONObject commentObj = new JSONObject();
                commentObj.put("text", postComment.getText());
                commentObj.put("author", GetUserName(postComment.getAuthorId()));
                commentsArr.put(commentObj);
            }
            final int numLikes = postLikes.size();
            final int numComments = postComments.size();
            obj.put("name", GetUserName(post.getAuthorId()));
            obj.put("numLikes", numLikes);
            obj.put("numComments", numComments);
            obj.put("comments", commentsArr);
            obj.put("codeBlock", post.getText());
            obj.put("language", "cpp");
            obj.put("problemName", GetProblemName(post.getProblemId()));
            obj.put("problemLink", GetProblemLink(post.getProblemId()));
            jsonArray.put(obj);
        }
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("data", jsonArray).toString();
    }

    private String GetProblemName(String problemId) {
        ProblemDescription problemDescription = this.problemDescriptionRepository.getByProblemId(problemId);
        if (problemDescription == null) return "";
        return problemDescription.getTitle();
    }

    private String GetProblemLink(String problemId) {
        ProblemDescription problemDescription = this.problemDescriptionRepository.getByProblemId(problemId);
        if (problemDescription == null) return "";
        return problemDescription.getUrl();
    }

    private String GetUserName(String userId) {
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);
        if (userProfile == null) return "";
        return userProfile.getName();
    }

    private String addComment(AddCommentHTTPRequest request) {
        return new JSONObject().toString();
    }

    private String addLike(AddLikeHTTPRequest request) {
        return new JSONObject().toString();
    }

    private String GetProblems() {
        List<ProblemDescription> problems = this.problemDescriptionRepository.findAll();
        final int maxSize = problems.size();
        JSONArray ar = new JSONArray();
        for (ProblemDescription problemDescription : problems) {
            JSONObject object = new JSONObject();
            object.put("problemIndex", problemDescription.getIndex())
                    .put("problemId", problemDescription.getProblemId())
                    .put("problemUrl", problemDescription.getUrl())
                    .put("problemComplexity", problemDescription.getComplexity())
                    .put("problemTitle", problemDescription.getTitle());
            ar.put(object);
        }
        String problemOfTheDay = GetProblemOfTheDay("daily");
        return new JSONObject().put("data", SortByIndex(ar)).put("size", maxSize).put("problemOfTheDay", problemOfTheDay).toString();
    }

    private String runCode(RunCodeHTTPRequest request) {
        CodeJudge judge = new CodeJudge(this.problemInputRepository);
        JSONObject response = judge.run(request.getSource_code(),
                request.getStdin(), request.getLanguage_id());
        return response.toString();
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

    private String GetProblemBaseCode(String problemId, String languageId) {
        ProblemBaseCode problemBaseCode =
                this.problemBaseCodeRepository.findByProblemIdAndLanguage(problemId, Utils.GetLanguageFromId(languageId));
        System.out.printf("ProblemId, Language: {%s, %s}\n", problemId, Utils.GetLanguageFromId(languageId));
        return new JSONObject()
                .put("message", "Success")
                .put("base_code", problemBaseCode.getBaseCode())
                .toString();
    }

    private String SubmitCode(SubmitCodeHTTPRequest request) {
        UserSubmission userSubmission = new UserSubmission();
        userSubmission.setUserId(request.getUserId());
        userSubmission.setProblemName(request.getProblemName());
        userSubmission.setProblemLink(request.getProblemLink());
        userSubmission.setSolutionLink(request.getSolutionLink());
        this.userSubmissionRepository.save(userSubmission);
        JSONObject obj = getUserStats(request.getUserId(), "", "");
        return new JSONObject().put("message", "Success")
                .put("userStats", obj)
                .toString();
    }

    // TOOD(Ravi): Add support for running the code through the Code Judge to get the metrics.
    private String SubmitCodeSolution(SubmitCodeSolutionHTTPRequest request) {
        // Step-I: Get the base code.
        CodeJudge judge = new CodeJudge(this.problemInputRepository);
        JSONObject response = judge.evaluate(request.getSolutionCode(), request.getSolutionCode(), request.getLanguageId());

        // Step-I: Save the code submission to the DB.
        CodeSubmission codeSubmission = new CodeSubmission();
        if (Utils.IsSuccess(response)) {
            codeSubmission.setUserId(request.getUserId());
            codeSubmission.setAccepted(true);
            codeSubmission.setSolutionCode(request.getSolutionCode());
            codeSubmission.setLanguage(Utils.GetLanguageFromId(request.getLanguageId()));
            codeSubmission.setProblemId(request.getProblemId());
            if (response.has("time")) {
                codeSubmission.setRunningTimeMS(response.getLong("time"));
            }
            if (response.has("memory")) {
                codeSubmission.setMemoryConsumption(response.getLong("memory"));
            }
            this.codeSubmissionRepository.save(codeSubmission);

            // Step-II: Save to User Input Repository.
            ProblemDescription problem = this.problemDescriptionRepository.getByProblemId(request.getProblemId());
            UserSubmission userSubmission = new UserSubmission();
            userSubmission.setUserId(request.getUserId());
            userSubmission.setProblemName(problem.getTitle());
            userSubmission.setProblemLink(problem.getUrl());
            userSubmission.setSolutionLink(codeSubmission.getSubmissionId());
            this.userSubmissionRepository.save(userSubmission);

            // Step-III: Create a user post.
            UserPost userPost = new UserPost();
            userPost.setLanguage(Utils.GetLanguageFromId(request.getLanguageId()));
            userPost.setProblemId(request.getProblemId());
            userPost.setProblemName(problem.getTitle());
            userPost.setPostType("CODE_REVIEW");
            userPost.setText(request.getSolutionCode());
            this.userPostRepository.save(userPost);
            return new JSONObject().put("message", "Success").toString();
        }
        return new JSONObject().put("message", "Error").toString();
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
        UserProfile userProfile = new UserProfile(userId, request.getEmail());
        if (oldProfile != null) {
            System.out.printf("Previous profile of the user: {%s}\n", oldProfile);
            userProfile.setReferrerId(oldProfile.getReferrerId());
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            userProfile.setName(request.getName());
        }
        if (request.getOrg() != null && !request.getOrg().isEmpty()) {
            userProfile.setOrg(request.getOrg());
        }
        this.userProfileRepository.save(userProfile);
        return new JSONObject().put("message", "Success").toString();
    }

    private String GetProblemById(String problemId) {
        ProblemDescription problemDescription = this.problemDescriptionRepository.getByProblemId(problemId);
        if (problemDescription != null) {
            String jsonString = new JSONObject()
                    .put("problemTitle", problemDescription.getTitle())
                    .put("problemIndex", problemDescription.getIndex())
                    .put("problemLink", problemDescription.getUrl())
                    .put("description", problemDescription.getDescription())
                    .toString();
            return jsonString;
        }
        return "{}";
    }

    private String GetProblemOfTheDay(String logic) {
        boolean getRandom = false;
        if (logic.equalsIgnoreCase("random")) {
            getRandom = true;
        }

        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date startDate = parser.parse(Constants.START_DATE);
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Date currentDate = Date.from(Instant.from(nowAsiaIndia));
            long diffInMillis = Math.abs(currentDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            if (getRandom) diffInDays = ThreadLocalRandom.current().nextLong(Constants.MAX_NUM_PROBLEMS);
            ProblemDescription problemDescription = this.problemDescriptionRepository.getByIndex(diffInDays);
            while (problemDescription == null) {
                ++diffInDays;
                problemDescription = this.problemDescriptionRepository.getByIndex(diffInDays);
            }
            if (problemDescription != null) {
                String jsonString = new JSONObject()
                        .put("problemTitle", problemDescription.getTitle())
                        .put("problemIndex", problemDescription.getIndex())
                        .put("problemLink", problemDescription.getUrl())
                        .put("description", problemDescription.getDescription())
                        .put("problemId", problemDescription.getProblemId())
                        .toString();
                return jsonString;
            }
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
        return "{}";
    }

    private static Date firstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    private int GetNumberOfProblemSubmissions(List<UserSubmission> submissions) {
        Set<String> submissionSet = new HashSet<>();
        for (UserSubmission userSubmission : submissions) {
            submissionSet.add(userSubmission.getProblemLink());
        }
        return submissionSet.size();
    }

    private JSONObject getUserStats(String userId, String userName, String timeFilter) {
        List<UserSubmission> submissions;

        // Step-I: Calculate start of the start this week.
        if (timeFilter.equalsIgnoreCase("WEEK")) { // TimeFilter = This Week
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Instant currentDate = firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia))).toInstant();
            submissions = this.userSubmissionRepository.findByUserIdAndCreatedAtGreaterThan(userId, currentDate);
        } else { // TimeFilter = ANY-Time
            submissions = this.userSubmissionRepository.findByUserId(userId);
        }
        HashSet<Long> datesSet = new HashSet<>();
        ArrayList<Long> datesList = new ArrayList<>();
        int referralCount = 0;
        int numberUniqueDays = 0;
        int longestStreak = 0;
        int numberOfSubmissions = GetNumberOfProblemSubmissions(submissions);
        try {
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date startDate = parser.parse(Constants.START_DATE);
            // Step-I: Get the number of Unique Submission Days.
            for (int idx = 0; idx < submissions.size(); ++idx) {
                Instant i = submissions.get(idx).getCreatedAt();
                Date endDate = Date.from(i);
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
            if (diff <= 2) {
                currRun++;
                longestStreak = max(longestStreak, currRun);
            } else {
                currRun = 1;
            }
        }
        if (datesList.size() > 0) longestStreak++;
        return new JSONObject()
                .put("name", userName)
                .put ("numberOfSubmissions", numberOfSubmissions)
                .put("numberUniqueDays", numberUniqueDays)
                .put("referralCount", referralCount)
                .put("longestStreak", longestStreak);
    }

    private String GetProfile(String userId) {
        // Step-I: Get data from the database.
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);
        if (userProfile == null) return "{}";
        String userName = userProfile.getName();
        if (userName == null || userName.isEmpty()) {
            userName = "Coding Ninja";
        }
        JSONObject userStats = getUserStats(userProfile.getUserId(), userName, "ANY_TIME");

        // Step-II: Translate this into a JSON & send response back to the client.
        String jsonString = new JSONObject()
                .put("name", userProfile.getName())
                .put("email", userProfile.getEmailId())
                .put("org", userProfile.getOrg())
                .put("numberUniqueDays", userStats.getInt("numberUniqueDays"))
                .put("numberOfSubmissions", userStats.getInt("numberOfSubmissions"))
                .put("longestStreak", userStats.getInt("longestStreak"))
                .toString();
        return jsonString;
    }

    private String getLeaderBoard(String timeFilter) {
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();
        JSONObject result = new JSONObject();
        JSONArray ar = new JSONArray();
        int idx = 0;
        final String userName = "Code Ninja -";
        for (UserProfile profile : userProfiles) {
            String profileName = profile.getName();
            if (profile.getName() == null || profile.getName().isEmpty()) {
                profileName = userName + idx;
            }
            JSONObject obj = getUserStats(profile.getUserId(), profileName, timeFilter);
            if (obj.has("numberUniqueDays") && obj.getInt("numberUniqueDays") > 0) {
                  ar.put(obj);
                  idx++;
            }
        }
        result.put("data", Sort(ar));
        return result.toString();
    }

    private JSONArray Sort(JSONArray jsonArr) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<>() {
            private static final String KEY_NAME = "numberUniqueDays";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                int valA = 0;
                int valB = 0;

                try {
                    valA = a.getInt(KEY_NAME);
                    valB = b.getInt(KEY_NAME);
                } catch (JSONException e) {
                    System.out.println(e);
                }

                return valA - valB;
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonArr.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
    }

    private JSONArray SortByIndex(JSONArray jsonArr) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<>() {
            private static final String KEY_NAME = "problemIndex";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                int valA = 0;
                int valB = 0;

                try {
                    valA = a.getInt(KEY_NAME);
                    valB = b.getInt(KEY_NAME);
                } catch (JSONException e) {
                    System.out.println(e);
                }

                return valA - valB;
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonArr.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;

    }
}
