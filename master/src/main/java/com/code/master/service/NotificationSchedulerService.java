package com.code.master.service;

import ch.qos.logback.classic.Logger;
import com.code.master.common.Email;

import com.code.master.data.UserProfile;
import com.code.master.data.UserProfileRepository;

import com.code.master.data.UserSubmissionRepository;
import com.code.master.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

import java.util.*;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

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
    // schedule at 10 am everyday . to-do : move this to prop file later,also try to disable the Scheduled in dev setups,
    // else every dev env will push emails.
    @Scheduled(cron = "0 0 3 * * *")
    public boolean sendDailyProblemAndLeaderBoardToAllUsers(){
        System.out.println("Sending an Email");

        boolean sentNotificationsToAllUsers=true;
        // send Leadership board details in the future ,commenting it for now
        String leaderBoard=statsService.getLeaderBoard();
        // to-do : The problem with the below line is that this call loads the user table in the memory
        // in future make findAll paginated , process it page by page and clear the users list after processing the batch
        // Also,there is no track of which users were processed to receive the email and thus we cannot re-try the ones who are skipped due to planned or unplanned outages/crashes dueing sending notifications
        List<UserProfile> users = userProfileRepository.findAll();
        // send the notification to all users, if you choose to send to only active users write a new FindBy in userProfileRepository
        for(UserProfile user : users){
            Email email = new Email();
            email.setTo(user.getEmailId());
            email.setSubject("Problem of the Day! Solve 1 problem a day. Win an IPhone.");
            email.setTemplate(env.getProperty("notification.daily.stats.template"));
            Map<String, Object> properties = new HashMap<>();
            // if the user has not provided the name send an empty string to the html template
            String userNameToSendEmailTo=(user.getName()!=null&& !user.getName().isEmpty()) ? user.getName(): "";
            properties.put("userNameToSendEmailTo", userNameToSendEmailTo);

            // Getting the problem designed for te day and not random, move to a configurable param in applications.properties
            String problem = problemsService.getProblemOfTheDay("");
            JSONObject jsonObj = new JSONObject(problem);
            // Retrieve the details of the problem add to the final HTML.
            properties.put("problemTitle", jsonObj.getString("problemTitle"));
            properties.put("problemLink", jsonObj.getString("problemLink"));
            email.setProperties(properties);
            System.out.printf("Problem properties: {%s}\n", properties.toString());
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
        final Context context = new Context();
        context.setVariables(email.getProperties());
        //TO-DO :: Change Email class to hold userprofile and print user id here than email in the logs
        System.out.printf("Sending notification to : {} ", email.getTo());
        final String senderName = "300 Days Of Code";
        // This address must be verified with Amazon SES.
        final String senderEmail = "admin@threehundreddaysofcode.com";
        final String source = senderName + "<" + senderEmail + ">";
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email.getTo()))
                    .withSource("300 Days Of Code")
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(templateEngine.process(email.getTemplate(), context))))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(email.getSubject())))
                    .withSource(source);
            client.sendEmail(request);
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: " + ex.getMessage());
        }
    }
}