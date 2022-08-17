
# Introduction
This repository serves the backend system for [The Three Hundred Days Of Code Challenge](https://www.threehundreddaysofcode.com/).<br />
To learn more about the challenge please check out [this link](https://www.threehundreddaysofcode.com/faq). 

# Instructions to setup the backend

1. Download the repo using the command:<br />
`git clone git@github.com:ravitandon90/ThreeHundredDaysOfCode-BE.git`
2. Setup Docker using [this link](https://docs.docker.com/get-docker/).
3. Go to root-directory using the command: `cd ThreeHundredDaysOfCode-BE`
4. Build the backend using the following command. `mvn clean package`
5. Deploy the backend on local using the following commands:<br />
   * `cd deployments/local` 
   * `docker-compose up`
6. Verify that the `Master Service` is up using the ping command as follows:
   * `curl localhost:80` <br />
   You should get `Master-Ok` as the response. 
7. Run the following command to list all the running docker containers:
   * `docker container ls` <br />
   You should see a Master Service and a Postgres server running.
8. Setup the database:  

# Three Hundred Days Of Code Challenge
Here is some information around the three hundred days of code challenge.
## What is the challenge?
Your goal is to solve 1 coding problem every single day. After you solve the problem, submit the problem on ThreeHundredDaysOfCode and send a tweet with the hashtag (#300DaysOfCode).

## What are the prizes?
There are three prizes:
1. Monthly Coding Streak Challenge: Every month we will give out $200 to the person who submits a solution on the maximum number of days . You need a minimum of 20 unique days to qualify for the prize.
2. Monthly Referral Challenge: Every month we will give out $50 to the person who brings in the maximum number of people to the community. You need a minimum of 10 successful referrals to qualify for the prize.
3. The Coding God: The first person to complete a 300 day long streak, will get a brand new IPhone. The solution should be your own and not copied from any other place.

# Contact
  * Ravi Tandon (LinkedIn, Email)
