package com.code.master.service;

import com.code.master.common.*;
import com.code.master.data.*;
import com.code.master.judge.CodeJudge;
import com.code.master.utils.Utils;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
import static java.lang.Math.min;

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
    @Autowired
    private UserNotificationRepository userNotificationRepository;
    @Autowired
    private UserFollowerRepository userFollowerRepository;

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
    @GetMapping(path = "/google/post")
    public String handleGoogleGetPost(@RequestParam(value = "postId") String postId) {
        return getPost(postId);
    }
    @GetMapping(path = "/post")
    public String handleGetPost(@RequestParam(value = "postId") String postId) { return getPost(postId); }

    @GetMapping(path = "/google/feed")
    public String handleGoogleGetFeed(@RequestParam(value = "userId") String userId,
                                      @RequestParam(value = "pageId") String pageId) {
        return getFeed(userId, pageId);
    }
    @GetMapping(path = "/google/notifications")
    public String handleGoogleGetNotifications(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "pageId") String pageId) {
        return getNotifications(userId, pageId);
    }

    @GetMapping(path = "/notifications")
    public String handleGetNotifications(
            @RequestParam(value = "pageId") String pageId, Principal user) {
        return getNotifications(user.getName(), pageId);
    }

    @GetMapping(path = "/google/numberOfNotifications")
    public String handleGoogleGetTotalNotifications(@RequestParam(value = "userId") String userId) {
        return GetTotalNotifications(userId);
    }

    @GetMapping(path = "/numberOfNotifications")
    public String handleGetTotalNotifications(Principal user) {
        return GetTotalNotifications(user.getName());
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

    private String getPost(String postId) {
        UserPost post = this.userPostRepository.getByPostId(postId);
        if (post == null) {
            return new JSONObject()
                    .put("message", "Error") // Move "message" to "status".
                    .put("data", "{}").toString();
        }
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
        int numLikes = postLikes.size();
        final int numComments = postComments.size();
        obj.put("postId", post.getPostId());
        obj.put("authorName", GetUserName(post.getAuthorId()));
        obj.put("numLikes", numLikes);
        obj.put("numComments", numComments);
        obj.put("comments", commentsArr);
        obj.put("codeBlock", post.getText());
        obj.put("language", "cpp");
        obj.put("problemName", GetProblemName(post.getProblemId()));
        obj.put("problemLink", GetProblemLink(post.getProblemId()));
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("data", obj).toString();
    }

    private String getFeed(String userId, String pageId) {
        // Step-I: Get all the user posts.
        List<UserPost> userPosts = this.userPostRepository.findAllByOrderByCreatedAtDesc();

        int pageIntId = Integer.parseInt(pageId);
        int startIdx = (pageIntId - 1) * Constants.FEED_PAGE_SIZE;
        int endIdx = min(userPosts.size(), startIdx + Constants.FEED_PAGE_SIZE);

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
            int numLikes = postLikes.size();
            final int numComments = postComments.size();
            obj.put("postId", post.getPostId());
            obj.put("authorName", GetUserName(post.getAuthorId()));
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

    private String getNotifications(String userId, String pageId) {
        List<UserNotification> userNotifications = this.userNotificationRepository.findAllByToUserIdOrderByCreatedAtDesc(userId);
        int pageIntId = Integer.parseInt(pageId);
        int startIdx = (pageIntId - 1) * Constants.NOTIFICATIONS_PAGE_SIZE;
        int endIdx = min(userNotifications.size(), startIdx + Constants.NOTIFICATIONS_PAGE_SIZE);
        userNotifications = userNotifications.subList(startIdx, endIdx);
        JSONArray jsonArray = new JSONArray();
        for (UserNotification userNotification : userNotifications) {
            JSONObject obj = new JSONObject();
            obj.put("notificationType", userNotification.getType());
            // TODO(Ravi): This needs to be optimized.
            obj.put("sourceAuthorName", GetUserName(userNotification.getFromUserId()));
            obj.put("sourceAuthorId", userNotification.getFromUserId());
            obj.put("createdAt", userNotification.getCreatedAt());
            obj.put("postId", userNotification.getPostId());
            obj.put("postText", "");
            jsonArray.put(obj);
        }
        UpdateNotificationsAsSeen(userNotifications);
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("data", jsonArray).toString();
    }

    @Async
    private void UpdateNotificationsAsSeen(List<UserNotification> userNotifications) {
        for (UserNotification userNotification : userNotifications) {
          userNotification.setSeen(true);
           this.userNotificationRepository.save(userNotification);
        }
    }

    private String GetTotalNotifications(String userId) {
        int numberNotifications = this.userNotificationRepository.getCountNotifications(userId);
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("numberNotifications", numberNotifications).toString();
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
        PostComment comment = new PostComment();
        comment.setAuthorId(request.getUserId());
        comment.setPostId(request.getPostId());
        comment.setText(request.getText());
        this.postCommentRepository.save(comment);
        List<PostComment> postComments = this.postCommentRepository.findAllByPostId(request.getPostId());
        JSONArray commentsArr = new JSONArray();
        for (PostComment postComment : postComments) {
            JSONObject commentObj = new JSONObject();
            commentObj.put("text", postComment.getText());
            commentObj.put("author", GetUserName(postComment.getAuthorId()));
            commentObj.put("commentId", postComment.getCommentId());
            commentsArr.put(commentObj);
        }
        CreateCommentNotification(request.getUserId(), request.getText(), request.getPostId());
        // Create a notification.
        return new JSONObject().put("message", "Success").put("data", commentsArr).toString();
    }

    private String GetUserId(String postId) {
        UserPost userPost =   this.userPostRepository.getByPostId(postId);
        String userId = userPost == null ? "" : userPost.getAuthorId();
        return userId;
    }

    @Async
    private void CreateCommentNotification(String userId, String text, String postId) {
        String toUserId = GetUserId(postId);
        UserNotification userNotification = new UserNotification();
        userNotification.setToUserId(toUserId);
        userNotification.setPostId(postId);
        userNotification.setFromUserId(userId);
        userNotification.setCommentText(text);
        userNotification.setType("USER_COMMENT");
        this.userNotificationRepository.save(new UserNotification());
    }

    @Async
    private void CreateLikeNotification(String userId, String postId) {
        String toUserId = GetUserId(postId);
        UserNotification userNotification = new UserNotification();
        userNotification.setToUserId(toUserId);
        userNotification.setPostId(postId);
        userNotification.setFromUserId(userId);
        userNotification.setType("USER_LIKE");
        this.userNotificationRepository.save(new UserNotification());
    }

    private String addLike(AddLikeHTTPRequest request) {
        PostLike like = new PostLike();
        like.setLike(request.isLiked());
        like.setAuthorId(request.getUserId());
        like.setPostId(request.getPostId());
        this.postLikeRepository.save(like);
        CreateLikeNotification(request.getUserId(), request.getPostId());
        return new JSONObject().put("message", "Success").toString();
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
        JSONObject response = judge.evaluate(request.getSource_code(), request.getProblem_id(), request.getLanguage_id());

        // Step-I: Save the code submission to the DB.
        CodeSubmission codeSubmission = new CodeSubmission();
        if (Utils.IsSuccess(response)) {
            codeSubmission.setUserId(request.getUser_id());
            codeSubmission.setAccepted(true);
            codeSubmission.setSolutionCode(request.getSource_code());
            codeSubmission.setLanguage(Utils.GetLanguageFromId(request.getLanguage_id()));
            codeSubmission.setProblemId(request.getProblem_id());
            if (response.has("time")) {
                codeSubmission.setRunningTimeMS(response.getLong("time"));
            }
            if (response.has("memory")) {
                codeSubmission.setMemoryConsumption(response.getLong("memory"));
            }
            this.codeSubmissionRepository.save(codeSubmission);

            // Step-II: Save to User Input Repository.
            ProblemDescription problem = this.problemDescriptionRepository.getByProblemId(request.getProblem_id());
            UserSubmission userSubmission = new UserSubmission();
            userSubmission.setUserId(request.getUser_id());
            userSubmission.setProblemName(problem.getTitle());
            userSubmission.setProblemLink(problem.getUrl());
            userSubmission.setSolutionLink(codeSubmission.getSubmissionId());
            this.userSubmissionRepository.save(userSubmission);

            // Step-III: Create a user post.
            UserPost userPost = new UserPost();
            userPost.setLanguage(Utils.GetLanguageFromId(request.getLanguage_id()));
            userPost.setProblemId(request.getProblem_id());
            userPost.setProblemName(problem.getTitle());
            userPost.setPostType("CODE_REVIEW");
            userPost.setText(request.getSource_code());
            userPost.setAuthorId(request.getUser_id());
            this.userPostRepository.save(userPost);
        }
        return response.toString();
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        System.out.println("Start of this week: " + cal.getTime());
        return cal.getTime();
    }

    private int GetNumberOfProblemSubmissions(List<UserSubmission> submissions) {
        Set<String> submissionSet = new HashSet<>();
        for (UserSubmission userSubmission : submissions) {
            submissionSet.add(userSubmission.getProblemLink());
        }
        return submissionSet.size();
    }

    private JSONObject getUserStatsFromSubmissions(String userId, String userName, Map<String, List<UserSubmission>> userSubmissionMap) {
        List<UserSubmission> submissions = userSubmissionMap.get(userId);
        int referralCount = 0;
        int numberUniqueDays = 0;
        int longestStreak = 0;
        int numberOfSubmissions = 0;
        if (submissions != null) {
            HashSet<Long> datesSet = new HashSet<>();
            ArrayList<Long> datesList = new ArrayList<>();
            numberOfSubmissions = GetNumberOfProblemSubmissions(submissions);
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
        }
        return new JSONObject()
                .put("name", userName)
                .put ("numberOfSubmissions", numberOfSubmissions)
                .put("numberUniqueDays", numberUniqueDays)
                .put("referralCount", referralCount)
                .put("longestStreak", longestStreak);

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
        // Step-I: Get all user profiles.
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();

        // Step-II: Get all user submissions.
        Map<String, List<UserSubmission>> userSubmissions = GetAllSubmissions(timeFilter);

        JSONObject result = new JSONObject();
        JSONArray ar = new JSONArray();
        int idx = 0;
        final String userName = "Code Ninja -";
        for (UserProfile profile : userProfiles) {
            String profileName = profile.getName();
            if (profile.getName() == null || profile.getName().isEmpty()) {
                profileName = userName + idx;
            }
            JSONObject obj = getUserStatsFromSubmissions(profile.getUserId(), profileName, userSubmissions);
            if (obj.has("numberUniqueDays") && obj.getInt("numberUniqueDays") > 0) {
                  ar.put(obj);
                  idx++;
            }
        }
        result.put("data", Sort(ar));
        return result.toString();
    }

    private Map<String, List<UserSubmission>> GetAllSubmissions(String timeFilter) {
        List<UserSubmission> allSubmissions;
        Map<String, List<UserSubmission>> userSubmissionMap = new HashMap<>();
        if (timeFilter.equalsIgnoreCase("LAST_WEEK")) {
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Date currentDate = firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia)));
            Date lastWeekDate = DateUtils.addDays(currentDate, -7);
            Instant currentDateInstant = lastWeekDate.toInstant();
            allSubmissions = this.userSubmissionRepository.findByCreatedAtGreaterThan(currentDateInstant);
        } else if (timeFilter.equalsIgnoreCase("WEEK")) { // TimeFilter = This Week
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Instant currentDate = firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia))).toInstant();
            allSubmissions = this.userSubmissionRepository.findByCreatedAtGreaterThan(currentDate);
        } else { // TimeFilter = ANY-Time
            allSubmissions = this.userSubmissionRepository.findAll();
        }
        for (UserSubmission submission: allSubmissions) {
            List<UserSubmission> userSubmissions = userSubmissionMap.get(submission.getUserId());
            if (userSubmissions == null) {
                userSubmissions = new ArrayList<>();
            }
            userSubmissions.add(submission);
            userSubmissionMap.put(submission.getUserId(), userSubmissions);
        }
        return userSubmissionMap;
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
