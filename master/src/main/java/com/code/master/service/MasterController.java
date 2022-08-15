package com.code.master.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {
    @GetMapping(path = "/")
    public String handlePing() { return "Master-Ok"; }

    @GetMapping(path = "/me")
    public String handleGetProfile() { return "{}"; }

    @GetMapping(path = "/leaderboard")
    public String handleGetLeaderBoard() { return "{}"; }

    @GetMapping(path = "/problem")
    public String handleGetProblemOfTheDay() { return "{}"; }

    @PostMapping(path = "/submitCode")
    public String handleCodeSubmission() {return "{}"; }
}
