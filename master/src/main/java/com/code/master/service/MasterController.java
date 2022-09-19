package com.code.master.service;

import com.code.master.common.*;
import com.code.master.data.*;
import com.code.master.index.QueryCompletion;
import com.code.master.index.SearchResultWrapper;
import com.code.master.index.monitor.IndexChangeMonitor;
import com.code.master.index.repository.ProblemDocumentRepository;
import com.code.master.index.repository.UserDocumentRepository;
import com.code.master.judge.CodeJudge;
import com.code.master.utils.Utils;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    @Autowired
    private UserDocumentRepository userDocumentRepository;
    @Autowired
    private ProblemDocumentRepository problemDocumentRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /*********************************** End Of API Definitions. *****************************************/
    @GetMapping(path = "/")
    public String handlePing() {return "Master-Ok"; }

    @GetMapping(path = "/me")
    public String handleGetProfile(Principal user) {
        return getUserProfile(user.getName());
    }

    @GetMapping(path = "/google/me")
    public String handleGoogleGetProfile(@RequestParam(value = "userId") String userId) {
        return getUserProfile(userId);
    }

    @GetMapping("/google/leaderBoard")
    public String handleGetLeaderBoard(@RequestParam(value = "userId") String userId, @RequestParam(value = "timeFilter") String timeFilter) {
        return getLeaderBoard(userId, timeFilter);
    }

    @GetMapping(path = "/leaderBoard")
    public String handleGetLeaderBoard(
            Principal user,
            @RequestParam(value = "timeFilter") String timeFilter) {
        return getLeaderBoard(user.getName(), timeFilter);
    }

    @GetMapping(path = "/mySubmissions")
    public String handleGetMySubmissions(Principal user, @RequestParam(value = "userId") String userId) {
        return GetSubmissionsForAUser(user.getName());
    }

    @GetMapping(path = "/google/mySubmissions")
    public String handleGetMySubmissions(@RequestParam(value = "userId") String userId) {
        return GetSubmissionsForAUser(userId);
    }

    @GetMapping(path = "/submissions")
    public String handleGetAllSubmissions(Principal user, @RequestParam(value = "pageId") String pageId) {
        return getAllSubmissionsAccessibleToAUser(user.getName(), pageId);
    }

    @GetMapping(path = "/google/submissions")
    public String handleGetAllSubmissions(@RequestParam(value = "userId") String userId,
                                          @RequestParam(value = "pageId") String pageId) {
        return getAllSubmissionsAccessibleToAUser(userId, pageId);
    }

    @GetMapping(path = "/submissionsProblem")
    public String handleGetSubmissionsForAProblem(Principal user,
                                          @RequestParam(value = "problemId") String problemId,
                                          @RequestParam(value = "pageId") String pageId) {
        return getSubmissionsForAProblem(user.getName(), pageId, problemId);
    }

    @GetMapping(path = "/google/submissionsProblem")
    public String handleGoogleGetSubmissionsForAProblem(@RequestParam(value = "userId") String userId,
                                          @RequestParam(value = "problemId") String problemId,
                                          @RequestParam(value = "pageId") String pageId) {
        return getSubmissionsForAProblem(userId, pageId, problemId);
    }

    @GetMapping(path = "/problems")
    public String handleGetProblems(Principal user) { return getProblems(user.getName()); }

    @GetMapping(path = "/google/problems")
    public String handleGoogleGetProblems(@RequestParam(value = "userId") String userId) {
        return getProblems(userId);
    }

    @GetMapping(path = "/problem")
    public String handleGetProblemOfTheDay(Principal user, @RequestParam(value = "logic") String logic) { return
            getProblemOfTheDay(user.getName(), logic);
    }

    @GetMapping(path = "/google/problem")
    public String handleGoogleGetProblemOfTheDay(@RequestParam(value = "userId") String userId,
                                                 @RequestParam(value = "logic") String logic) {
        return getProblemOfTheDay(userId, logic);
    }

    @GetMapping(path = "/problemById")
    public String handleGetProblemById(Principal user, @RequestParam(value = "problemId") String problemId) { return GetProblemById(problemId); }

    @GetMapping(path = "/google/problemById")
    public String handleGoogleGetProblemById(@RequestParam(value = "problemId") String problemId) { return GetProblemById(problemId); }

    @GetMapping(path = "/problemBaseCode")
    public String handleGetProblemBaseCode(@RequestParam(value = "languageId") String languageId,@RequestParam(value = "problemId") String problemId) {
        return getProblemBaseCode(problemId, languageId);
    }

    @GetMapping(path = "/google/problemBaseCode")
    public String handleGoogleGetProblemBaseCode(@RequestParam(value = "languageId") String languageId, @RequestParam(value = "problemId") String problemId) {
        return getProblemBaseCode(problemId, languageId);
    }

    @PostMapping(path = "/submitCode")
    public String handleCodeSubmission(
            @RequestBody SubmitCodeHTTPRequest request, Principal user) { return submitCode(request); }


    @PostMapping(path = "/submitCodeSolution")
    public String handleSubmitCodeSolution(
            @RequestBody SubmitCodeSolutionHTTPRequest request) { return submitCodeSolution(request); }

    @PostMapping(path = "/google/submitCodeSolution")
    public String handleGoogleSubmitCodeSolution(@RequestBody SubmitCodeSolutionHTTPRequest request) {return submitCodeSolution(request);}

    @PostMapping(path = "/google/submitCode")
    public String handleGoogleSubmitCode(@RequestBody SubmitCodeHTTPRequest request) {
        return submitCode(request);
    }

    @PostMapping(path = "/createProfile")
    public String handleCreateProfile(
            @RequestBody CreateProfileHTTPRequest request, Principal user) {
        return createProfile(user.getName(), request.getEmailId(), request.getUserName(), request.getReferrerId());
    }

    @PostMapping(path = "/google/createProfile")
    public String handleCreateProfile(@RequestBody CreateProfileHTTPRequest request) {
        return createProfile(request.getUserId(), request.getEmailId(), request.getUserName(), request.getReferrerId());
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

    @GetMapping(path = "/google/timeRemaining")
    public String handleGetTimeRemaining() {
        return getTimeRemainingMilliseconds();
    }

    @GetMapping(path = "/google/submission")
    public String handleGetSubmission(@RequestParam(value = "submissionId") String submissionId) {
        return GetCodeSubmission(submissionId);
    }

    @GetMapping(path = "/google/autoComplete")
    public String handleGoogleAutoComplete(
            @RequestParam(value = "searchText") String searchText,
            @RequestParam(value = "userId") String userId) {
        return GetCompletions(searchText, userId);
    }

    @GetMapping(path = "/autoComplete")
    public String handleAutoComplete(
            Principal user,
            @RequestParam(value = "searchText") String searchText) {
        return GetCompletions(searchText, user.getName());
    }

    @GetMapping(path = "/google/search")
    public String handleGoogleSearch(
            @RequestParam(value = "searchText") String searchText,
            @RequestParam(value = "userId") String userId

    ) {
        return GetSearchResults(searchText, userId);
    }

    @GetMapping(path = "/search")
    public String handleSearch(
            Principal user,
            @RequestParam(value = "searchText") String searchText){
        return GetSearchResults(searchText, user.getName());
    }

    @PostMapping(path = "/google/buildIndex")
    public String handleTriggerIndexBuild() {
        Runnable indexChangeMonitorThread =
                new IndexChangeMonitor(
                        this.problemDocumentRepository,
                        this.userDocumentRepository,
                        this.problemDescriptionRepository,
                        this.userProfileRepository,
                        1,
                        0);
        new Thread(indexChangeMonitorThread).start();
        return "Index-Build-Triggered";
    }


    /*********************************** End Of API Definitions. *****************************************/

    private String GetCompletions(String searchText, String userId) {
        AutoComplete autoComplete = new AutoComplete(
                this.elasticsearchRestTemplate,
                this.problemDocumentRepository,
                this.userDocumentRepository);
        List<QueryCompletion> completions = autoComplete.complete(searchText, userId, "");
        return getOutputFromCompletions(completions);
    }
    private String GetSearchResults(String searchText, String userId) {
        AutoComplete autoComplete = new AutoComplete(
                this.elasticsearchRestTemplate,
                this.problemDocumentRepository,
                this.userDocumentRepository);
        List<SearchResultWrapper> searchResults = autoComplete.search(searchText, userId, "");
        return getOutputFromSearchResults(searchResults);
    }

    private String getOutputFromCompletions(List<QueryCompletion> completions) {
        JSONArray jsonArray = new JSONArray();
        for (QueryCompletion queryCompletion : completions) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("display_text", queryCompletion.getDisplayText());
            jsonObject.put("query_completion_type", queryCompletion.getQueryCompletionType());
            jsonArray.put(jsonObject);
        }
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("completions", jsonArray).toString();
    }

    private String getOutputFromSearchResults(List<SearchResultWrapper> searchResults) {
        JSONArray jsonArray = new JSONArray();
        for (SearchResultWrapper searchResultWrapper : searchResults) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", searchResultWrapper.getTitle());
            jsonObject.put("id", searchResultWrapper.getId());
            jsonObject.put("type", searchResultWrapper.getType());
            jsonObject.put("source", "");
            jsonObject.put("display_source_text", "");
            jsonObject.put("url", "");
            jsonObject.put("display_url", "");
            jsonObject.put("createdAt", "");
            if (searchResultWrapper.getDescription() != null) {
                jsonObject.put("description", searchResultWrapper.getDescription());
            }
            jsonObject.put("reporter", "");
            jsonArray.put(jsonObject);
        }
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("docs", jsonArray).toString();

    }

    @PostConstruct
    private void BuildIndex() {
        if (!Constants.RUN_CHANGE_MONITORS) return;
        System.out.println("In BuildIndex");

        Runnable indexChangeMonitorThread =
                new IndexChangeMonitor(
                        this.problemDocumentRepository,
                        this.userDocumentRepository,
                        this.problemDescriptionRepository,
                        this.userProfileRepository,
                        Integer.MAX_VALUE,
                        Constants.DOCUMENT_POLL_INTERVAL_MINUTES * 60 * 1000);
        new Thread(indexChangeMonitorThread).start();
    }

    private String GetCodeSubmission(String submissionId) {
        CodeSubmission codeSubmission = this.codeSubmissionRepository.getBySubmissionId(submissionId);
        JSONObject obj = new JSONObject();
        obj.put("submissionCode", codeSubmission.getSolutionCode());
        obj.put("language", codeSubmission.getLanguage());
        return new JSONObject()
                .put("message", "Success") // Move "message" to "status".
                .put("data", obj).toString();
    }

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
            obj.put("authorName", GetUserName(userNotification.getFromUserId()));
            obj.put("authorId", userNotification.getFromUserId());
            obj.put("createdAt", userNotification.getCreatedAt());
            obj.put("postId", userNotification.getPostId());
            obj.put("postText", userNotification.getCommentText());
            obj.put("problemName", GetProblemNameFromPostId(userNotification.getPostId()));
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

    private String GetProblemNameFromPostId(String postId) {
        UserPost userPost = this.userPostRepository.getByPostId(postId);
        if (userPost == null) return "";
        return GetProblemName(userPost.getProblemId());
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
        this.userNotificationRepository.save(userNotification);
    }

    @Async
    private void CreateLikeNotification(String userId, String postId) {
        String toUserId = GetUserId(postId);
        UserNotification userNotification = new UserNotification();
        userNotification.setToUserId(toUserId);
        userNotification.setPostId(postId);
        userNotification.setFromUserId(userId);
        userNotification.setType("USER_LIKE");
        this.userNotificationRepository.save(userNotification);
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

    private String getProblems(String userId) {
        final String groupId = getGroupIdForAUser(userId);
        List<ProblemDescription> problems = this.problemDescriptionRepository.findAllByGroupId(groupId);
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
        String problemOfTheDay = getProblemOfTheDay(userId, "daily");
        return new JSONObject().put("data", SortByIndex(ar)).put("size", maxSize).put("problemOfTheDay", problemOfTheDay).toString();
    }

    private String runCode(RunCodeHTTPRequest request) {
        CodeJudge judge = new CodeJudge(this.problemInputRepository);
        JSONObject response = judge.run(request.getSource_code(),
                request.getStdin(), request.getLanguage_id());
        return response.toString();
    }

    Map<String, String> getUserNameIdMap() {
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();
        Map<String, String> userNameIdMap = new HashMap<>();
        int idx = 1;
        for (UserProfile userProfile : userProfiles) {
            String userName = userProfile.getName();
            if (userName == null || userName.isEmpty()) {
                userName = "Coding Ninja " + idx;
                ++idx;
            }
            userNameIdMap.put(userProfile.getUserId(), userName);
        }
        return userNameIdMap;
    }

    private List<SubmissionWrapper> getMerged(List<UserSubmission> userSubmissions,
                                              List<CodeSubmission> codeSubmissions) {
        List<SubmissionWrapper> submissionWrappers = new ArrayList<>();
        for (UserSubmission userSubmission: userSubmissions) {
            SubmissionWrapper submissionWrapper = new SubmissionWrapper();
            submissionWrapper.setUserSubmission(userSubmission);
            submissionWrappers.add(submissionWrapper);
        }
        for (CodeSubmission codeSubmission: codeSubmissions) {
            SubmissionWrapper submissionWrapper = new SubmissionWrapper();
            submissionWrapper.setCodeSubmission(codeSubmission);
            submissionWrappers.add(submissionWrapper);
        }
        return submissionWrappers;
    }

    private String GetSubmissionsForAUser(String userId) {
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);

        List<UserSubmission> userSubmissions = this.userSubmissionRepository.findByUserId(userId);
        List<CodeSubmission> codeSubmissions = this.codeSubmissionRepository.findByUserId(userId);
        List<SubmissionWrapper> submissions = getMerged(userSubmissions, codeSubmissions);
        Map<String, ProblemDescription> problemIdDescriptionMap = getProblemIdNameMap();
        Map<String, ProblemDescription> problemUrlDescriptionMap = getProblemLinkNameMap();
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (SubmissionWrapper submission : submissions) {
            JSONObject submissionJSONObject = ToJSONObject(submission, userProfile, problemIdDescriptionMap, problemUrlDescriptionMap);
            if (submissionJSONObject != null) {
                arr.put(submissionJSONObject);
            }
        }
        object.put("data", arr);
        return object.toString();
    }

    private Map<String, ProblemDescription> getProblemIdNameMap() {
        Map<String, ProblemDescription> map = new HashMap<>();
        List<ProblemDescription> problems = this.problemDescriptionRepository.findAll();
        for (ProblemDescription problem : problems) {
            map.put(problem.getProblemId(), problem);
        }
        return map;
    }

    private Map<String, ProblemDescription> getProblemLinkNameMap() {
        Map<String, ProblemDescription> map = new HashMap<>();
        List<ProblemDescription> problems = this.problemDescriptionRepository.findAll();
        for (ProblemDescription problem : problems) {
            map.put(problem.getUrl(), problem);
        }
        return map;
    }

    private String getGroupIdForAUser(String userId) {
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);
        if (userProfile == null) return "";
        return userProfile.getGroupId();
    }

    private String getSubmissionsForAProblem(String userId, String pageId, String problemId) {
        final String groupId = getGroupIdForAUser(userId);
        ProblemDescription problemDescription = this.problemDescriptionRepository.getByProblemId(problemId);
        JSONObject object = new JSONObject();
        if (problemDescription != null) {
            final String problemName = problemDescription.getTitle();
            List<UserSubmission> userSubmissions = this.userSubmissionRepository.findByProblemNameAndGroupId(problemName, groupId);
            List<CodeSubmission> codeSubmissions = this.codeSubmissionRepository.getByProblemIdAndGroupId(problemId, groupId);
            List<SubmissionWrapper> submissions = getMerged(userSubmissions, codeSubmissions);
            Map<String, String> userNameIdMap = getUserNameIdMap();
            int pageIntId = Integer.parseInt(pageId);
            int startIdx = (pageIntId - 1) * Constants.FEED_PAGE_SIZE;
            int endIdx = min(submissions.size(), startIdx + Constants.FEED_PAGE_SIZE);
            submissions = submissions.subList(startIdx, endIdx);
            JSONArray arr = new JSONArray();
            for (SubmissionWrapper submission : submissions) {
                JSONObject submissionJSONObject = ToJSONObject(submission, problemDescription, userNameIdMap);
                arr.put(submissionJSONObject);
            }
            object.put("data", arr);
        }
        return object.toString();
    }

    private String getAllSubmissionsAccessibleToAUser(
            String userId, String pageId) {
        UserProfile userProfile = this.userProfileRepository.getByUserId(userId);
        final String groupId = userProfile.getGroupId();
        List<UserSubmission> submissions = this.userSubmissionRepository.findByGroupId(groupId);
        Map<String, String> userNameIdMap = getUserNameIdMap();
        int pageIntId = Integer.parseInt(pageId);
        int startIdx = (pageIntId - 1) * Constants.FEED_PAGE_SIZE;
        int endIdx = min(submissions.size(), startIdx + Constants.FEED_PAGE_SIZE);
        submissions = submissions.subList(startIdx, endIdx);
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (UserSubmission submission : submissions) {
            JSONObject submissionJSONObject = ToJSONObject(submission, userNameIdMap);
            arr.put(submissionJSONObject);
        }
        object.put("data", arr);
        return object.toString();
    }

    private JSONObject ToJSONObject(UserSubmission submission, Map<String, String> userIdNameMap) {
        JSONObject object = new JSONObject();
        String authorName = "";
        if (userIdNameMap.containsKey(submission.getUserId())) {
            authorName = userIdNameMap.get(submission.getUserId());
        }
        object.put("problemName", submission.getProblemName())
                .put("problemLink", submission.getProblemLink())
                .put("solutionLink", submission.getSolutionLink())
                .put("submissionDate", submission.getCreatedAt())
                .put("authorName", authorName);
        return object;
    }

    private JSONObject ToJSONObject(SubmissionWrapper submission,
                                    ProblemDescription problemDescription,
                                    Map<String, String> userIdNameMap) {
        JSONObject object = new JSONObject();
        String authorName = "";
        if (userIdNameMap.containsKey(submission.getUserId())) {
            authorName = userIdNameMap.get(submission.getUserId());
        }
        object.put("problemName", problemDescription.getTitle());
        object.put("problemLink", problemDescription.getUrl());
        object.put("problemId", problemDescription.getProblemId());
        object.put("submissionDate", submission.getCreatedAt());
        object.put("submissionId", submission.getSubmissionId());
        object.put("solutionLink", submission.getSolutionLink());
        object.put("authorName", authorName);
        return object;
    }

    private JSONObject ToJSONObject(SubmissionWrapper submission,
                                    UserProfile userProfile,
                                    Map<String, ProblemDescription> problemIdDescriptionMap,
                                    Map<String, ProblemDescription> problemUrlDescriptionMap) {
        JSONObject object = null;
        ProblemDescription problemDescription = null;
        String authorName = userProfile.getName();
        final String problemId = submission.getProblemId();
        final String problemUrl = submission.getProblemUrl();
        if (problemIdDescriptionMap.containsKey(problemId)) {
            problemDescription = problemIdDescriptionMap.get(problemId);
        } else if (problemUrlDescriptionMap.containsKey(problemUrl)) {
            problemDescription = problemUrlDescriptionMap.get(problemUrl);
        }
        if (problemDescription != null) {
            object = new JSONObject();
            object.put("problemName", problemDescription.getTitle());
            object.put("problemLink", problemDescription.getUrl());
            object.put("problemId", problemDescription.getProblemId());
            object.put("submissionDate", submission.getCreatedAt());
            object.put("submissionId", submission.getSubmissionId());
            object.put("solutionLink", submission.getSolutionLink());
            object.put("authorName", authorName);
        }
        return object;
    }

    private String getProblemBaseCode(String problemId, String languageId) {
        ProblemBaseCode problemBaseCode =
                this.problemBaseCodeRepository.findByProblemIdAndLanguage(problemId, Utils.GetLanguageFromId(languageId));
        return new JSONObject()
                .put("message", "Success")
                .put("base_code", problemBaseCode.getBaseCode())
                .toString();
    }

    private String submitCode(SubmitCodeHTTPRequest request) {
        final String userId = request.getUserId();
        UserSubmission userSubmission = new UserSubmission();
        userSubmission.setUserId(request.getUserId());
        userSubmission.setProblemName(request.getProblemName());
        userSubmission.setProblemLink(request.getProblemLink());
        userSubmission.setSolutionLink(request.getSolutionLink());
        final String groupId = getGroupIdForAUser(userId);
        userSubmission.setGroupId(groupId);
        this.userSubmissionRepository.save(userSubmission);
        JSONObject obj = getUserStats(request.getUserId(), "", "");
        return new JSONObject().put("message", "Success")
                .put("userStats", obj)
                .toString();
    }

    // TOOD(Ravi): Add support for running the code through the Code Judge to get the metrics.
    private String submitCodeSolution(SubmitCodeSolutionHTTPRequest request) {
        // Step-I: Get the base code.
        CodeJudge judge = new CodeJudge(this.problemInputRepository);
        JSONObject response = judge.evaluate(request.getSource_code(), request.getProblem_id(), request.getLanguage_id());
        final String userId = request.getUser_id();
        final String groupId = getGroupIdForAUser(userId);

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
            codeSubmission.setGroupId(groupId);
            this.codeSubmissionRepository.save(codeSubmission);

            // Step-II: Save to User Input Repository.
            ProblemDescription problem = this.problemDescriptionRepository.getByProblemId(request.getProblem_id());
            UserSubmission userSubmission = new UserSubmission();
            userSubmission.setUserId(request.getUser_id());
            userSubmission.setProblemName(problem.getTitle());
            userSubmission.setProblemLink(problem.getUrl());
            userSubmission.setSolutionLink(codeSubmission.getSubmissionId());
            userSubmission.setGroupId(groupId);
            this.userSubmissionRepository.save(userSubmission);

            // Step-III: Create a user post.
            UserPost userPost = new UserPost();
            userPost.setLanguage(Utils.GetLanguageFromId(request.getLanguage_id()));
            userPost.setProblemId(request.getProblem_id());
            userPost.setProblemName(problem.getTitle());
            userPost.setPostType("CODE_REVIEW");
            userPost.setText(request.getSource_code());
            userPost.setAuthorId(request.getUser_id());
            userPost.setGroupId(groupId);
            this.userPostRepository.save(userPost);
        }
        return response.toString();
    }

    private String createProfile(String userId, String userEmailId, String userName, String referrerId) {
        // Check if the profile already exists in the database.
        UserProfile profile = this.userProfileRepository.getByUserId(userId);
        // Create a new profile if it already does not exist in the database.
        if (profile == null) {
            // If not create the profile.
            UserProfile userProfile = new UserProfile(userId, userEmailId);
            userProfile.setName(userName);
            userProfile.setReferrerId(referrerId);
            // TODO(Ravi): This should come in from the frontend.
            userProfile.setGroupId(Constants.DEFAULT_USER_GROUP);
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

    private String getTimeRemainingMilliseconds() {
        long diffInMillis;
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        TimeZone tz = TimeZone.getTimeZone("EST");
        c.setTimeZone(tz);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 30);
        Date nextDate = c.getTime();
        diffInMillis = Math.abs(nextDate.getTime() - currentDate.getTime()) % (24 * 60 * 60 * 1000);
        return new JSONObject().put("message", "Success").put("diffInMillis", diffInMillis).toString();
    }

    private String getProblemOfTheDay(String userId, String logic) {
        boolean getRandom = false;
        JSONObject jsonObject = new JSONObject();
        if (logic.equalsIgnoreCase("random")) {
            getRandom = true;
        }
        final String groupId = getGroupIdForAUser(userId);
        try {
            SimpleDateFormat parser = new SimpleDateFormat(Constants.START_DATE_FORMAT);
            parser.setTimeZone(TimeZone.getTimeZone("IST"));
            Date startDate = parser.parse(Constants.START_DATE);
            Date currentDate = new Date();
            long diffInMillis = Math.abs(currentDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            if (getRandom) diffInDays = ThreadLocalRandom.current().nextLong(Constants.MAX_NUM_PROBLEMS);
            ProblemDescription problemDescription = this.problemDescriptionRepository.getByIndexAndGroupId(diffInDays, groupId);
            // Ideally, there should be no misses in the data with respect to diff-days.
            while (problemDescription == null) {
                ++diffInDays;
                problemDescription = this.problemDescriptionRepository.getByIndexAndGroupId(diffInDays, groupId);
            }
            if (problemDescription != null) {
                jsonObject
                        .put("problemTitle", problemDescription.getTitle())
                        .put("problemIndex", problemDescription.getIndex())
                        .put("problemLink", problemDescription.getUrl())
                        .put("description", problemDescription.getDescription())
                        .put("problemId", problemDescription.getProblemId());
            }
        } catch (ParseException e) {
            System.out.printf("Error parsing date: {%s}\n", e);
        }
        return jsonObject.toString();
    }



    private int GetNumberOfProblemSubmissions(List<UserSubmission> submissions) {
        Set<String> submissionSet = new HashSet<>();
        for (UserSubmission userSubmission : submissions) {
            submissionSet.add(userSubmission.getProblemLink());
        }
        return submissionSet.size();
    }

    private Date resetHours(Date in) {
        Calendar now = Calendar.getInstance();
        now.setTime(in);
        now.set(Calendar.HOUR_OF_DAY, 12);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    private JSONObject getUserStatsFromSubmissions(String userId, String userName,
                                                   Map<String, List<UserSubmission>> userSubmissionMap) {
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
                SimpleDateFormat parser = new SimpleDateFormat(Constants.START_DATE_FORMAT);
                Date startDate = resetHours(parser.parse(Constants.START_DATE));
                // Step-I: Get the number of Unique Submission Days.
                for (int idx = 0; idx < submissions.size(); ++idx) {
                    Instant i = submissions.get(idx).getCreatedAt();
                    Date endDate = resetHours(Date.from(i));
                    long diffInMillis = Math.abs(endDate.getTime() - startDate.getTime()) * 2;
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
                .put("userId", userId)
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
            Instant currentDate = Utils.firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia))).toInstant();
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
            SimpleDateFormat parser = new SimpleDateFormat(Constants.START_DATE_FORMAT);
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

    private String getUserProfile(String userId) {
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

    private String getLeaderBoard(String userId, String timeFilter) {
        // Step-I: Get group-id for the user.
        final String groupId = getGroupIdForAUser(userId);

        // Step-II: Get all user profiles.
        List<UserProfile> userProfiles = this.userProfileRepository.findByGroupId(groupId);

        // Step-III: Get all user submissions.
        Map<String, List<UserSubmission>> userSubmissions = getAllSubmissions(groupId, timeFilter);

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

    private Map<String, List<UserSubmission>> getAllSubmissions(String groupId, String timeFilter) {
        List<UserSubmission> allSubmissions;
        Map<String, List<UserSubmission>> userSubmissionMap = new HashMap<>();
        if (timeFilter.equalsIgnoreCase("LAST_WEEK")) {
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Date currentDate = Utils.firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia)));
            Date lastWeekDate = DateUtils.addDays(currentDate, -7);
            Instant currentDateInstant = lastWeekDate.toInstant();
            allSubmissions = this.userSubmissionRepository.findByGroupIdAndCreatedAtGreaterThan(groupId, currentDateInstant);
        } else if (timeFilter.equalsIgnoreCase("WEEK")) { // TimeFilter = This Week
            Instant nowUtc = Instant.now();
            ZoneId asiaIndia = ZoneId.of("Asia/Kolkata");
            ZonedDateTime nowAsiaIndia = ZonedDateTime.ofInstant(nowUtc, asiaIndia);
            Instant currentDate = Utils.firstDayOfWeek(Date.from(Instant.from(nowAsiaIndia))).toInstant();
            allSubmissions = this.userSubmissionRepository.findByGroupIdAndCreatedAtGreaterThan(groupId, currentDate);
        } else { // TimeFilter = ANY-Time
            allSubmissions = this.userSubmissionRepository.findByGroupId(groupId);
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
