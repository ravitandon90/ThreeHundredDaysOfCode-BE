package com.code.master.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.code.master.common.Email;

import com.code.master.data.UserProfile;
import com.code.master.data.UserProfileRepository;

import com.code.master.data.UserSubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSchedulerService {

    @Autowired
    private UserSubmissionRepository userSubmissionRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private StatsService statsService;

    @Autowired
    private final JavaMailSender emailSender;

    @Autowired
    private final SpringTemplateEngine templateEngine;

    @Autowired
    private Environment env;
    // schedule at 10 am everyday . to-do : move this to prop file later
    @Scheduled(cron = "0 00 10 ? * *")
    public boolean NotifyStatsToAllUsers(){

        String leaderBoard=statsService.GetLeaderBoard();
        // to-do : The problem with the below line is that this call loads the user table in the memory
        // in future make findAll paginated , process it page by page and clear the users list after processing the batch
        List<UserProfile> users = userProfileRepository.findAll();
        // send the notification to all users , if you choose to send oo only active users write a new FindBy in userProfileRepository
        for(UserProfile user : users){
            log.info("sending notification to user "+user.getUserId());
            Email email = new Email();
            email.setTo(user.getEmailId());
            email.setFrom(env.getProperty("spring.mail.username"));
            email.setSubject("Daily Stats- 300 Coding Challange");
            email.setTemplate(env.getProperty("notification.daily.stats.template"));
            Map<String, Object> properties = new HashMap<>();

            properties.put("data", LocalDate.now().toString());
            // add leaderborad list here for html in next checkin

            email.setProperties(properties);

            try {
                sendHtmlMessage(email);
                Thread.sleep(Long.parseLong(env.getProperty("wait.between.notifications")));
            } catch (MessagingException e) {
                e.printStackTrace();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        return false;
    }



    public String sendHtmlMessage(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);

        log.info("Sending email: {} with html body: {}", email, html);
        emailSender.send(message);
        return "sent";
    }
}