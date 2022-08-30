package com.code.master.service;

import com.code.master.common.Email;

import com.code.master.data.UserProfile;
import com.code.master.data.UserProfileRepository;

import com.code.master.data.UserSubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProblemsService problemsService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private final JavaMailSender emailSender;

    @Autowired
    private final SpringTemplateEngine templateEngine;

    @Autowired
    private Environment env;
    // schedule at 10 am everyday . to-do : move this to prop file later,also try to disable the Scheduled in dev setups, else every dev env will push emails
    @Scheduled(cron = "0 00 10 ? * *")
    public boolean sendDailyProblemAndLeaderBoardToAllUsers(){

        boolean sentNotificationsToAllUsers=true;
        // send Leadership board details in the future ,commenting it for now
        String leaderBoard=statsService.getLeaderBoard();
        // to-do : The problem with the below line is that this call loads the user table in the memory
        // in future make findAll paginated , process it page by page and clear the users list after processing the batch
        // Also,there is no track of which users were processed to receive the email and thus we cannot re-try the ones who are skipped due to planned or unplanned outages/crashes dueing sending notifications
        List<UserProfile> users = userProfileRepository.findAll();
        // send the notification to all users , if you choose to send to only active users write a new FindBy in userProfileRepository
        for(UserProfile user : users){
            Email email = new Email();
            email.setTo(user.getEmailId());
            email.setFrom(env.getProperty("spring.mail.username"));
            email.setSubject("Knock , knock - Problem of the Day ");
            email.setTemplate(env.getProperty("notification.daily.stats.template"));
            Map<String, Object> properties = new HashMap<>();
            // if the user has not provided the name sebd an empty string to the heml template
            String userNameToSendEmailTo=(user.getName()!=null&& !user.getName().isEmpty()) ? user.getName(): "";
            properties.put("userNameToSendEmailTo",userNameToSendEmailTo);

            // getting the problem designed for te day and not random, move to to a configurable param in applications.properties
            String problem= problemsService.getProblemOfTheDay("");
            JSONObject jsonObj = new JSONObject(problem);

            // retrieve the problems details and add to the final HTML .
            properties.put("problemName",jsonObj.getString("problemName"));
            properties.put("problemLink",jsonObj.getString("problemLink"));
            email.setProperties(properties);

            try {
                publishEmailNotificationToTheIndividualRecipient(email);
                // this is made configured to add a sleep to honor the SMTP server's rate limiting
                Thread.sleep(Long.parseLong(env.getProperty("wait.between.notifications")));
            } catch (MessagingException e) {
                sentNotificationsToAllUsers=false;
                e.printStackTrace();
            }catch(InterruptedException e){
                sentNotificationsToAllUsers=false;
                e.printStackTrace();
            }

        }

        return sentNotificationsToAllUsers;
    }



    public void publishEmailNotificationToTheIndividualRecipient(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        // Change Email class to hold userprofile and print user id here than email in the logs
        log.info("Sending notification to : {} ", email.getTo());
        emailSender.send(message);
    }
}