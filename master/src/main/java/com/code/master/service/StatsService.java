package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.data.UserProfile;
import com.code.master.data.UserProfileRepository;
import com.code.master.data.UserSubmission;
import com.code.master.data.UserSubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserSubmissionRepository userSubmissionRepository;

    public String getLeaderBoard() {
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
            JSONObject obj = getUserStats(profile.getUserId(), profileName);
            if (obj.has("numberUniqueDays") && obj.getInt("numberUniqueDays") > 0) {
                ar.put(obj);
                idx++;
            }
        }
        result.put("data", Sort(ar));
        return result.toString();
    }

    private String getUserStats(UserProfile user){
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
            JSONObject obj = getUserStats(profile.getUserId(), profileName);
            if (obj.has("numberUniqueDays") && obj.getInt("numberUniqueDays") > 0) {
                ar.put(obj);
                idx++;
            }
        }
        result.put("data", Sort(ar));
        return result.toString();
    }

    private JSONObject getUserStats(String userId, String userName) {
        List<UserSubmission> submissions = this.userSubmissionRepository.findByUserId(userId);
        HashSet<Long> datesSet = new HashSet<>();
        ArrayList<Long> datesList = new ArrayList<>();
        int referralCount = 0;
        int numberUniqueDays = 0;
        int longestStreak = 0;
        int numberOfSubmissions = submissions.size();
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
                .put ("numberOfSubmissions", numberOfSubmissions)
                .put("numberUniqueDays", numberUniqueDays)
                .put("referralCount", referralCount)
                .put("longestStreak", longestStreak);
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
}
