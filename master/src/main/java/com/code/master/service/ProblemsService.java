package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.data.ProblemDescription;
import com.code.master.data.ProblemDescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemsService {


    @Autowired
    private ProblemDescriptionRepository problemDescriptionRepository;


    public String getProblemOfTheDay(String logic) {
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


}
