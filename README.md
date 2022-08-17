# Introduction
This repository serves the backend system for [The Three Hundred Days Of Code Challenge](https://www.threehundreddaysofcode.com/).<br />
To learn more about the challenge please check out [this link](https://www.threehundreddaysofcode.com/faq).

Note: 
1. This setup is for running the cluster locally.
2. The codebase for the FE is present in [this repository](https://github.com/ravitandon90/fe-three-days-of-code). 

# Pre-Requisites
The following are the pre-requisites for setting up the backend cluster:
* Docker
* Git
* Java 
* MVN

# Instructions to setup the backend
1. Download the repo using the command:<br />
   * `git clone git@github.com:ravitandon90/ThreeHundredDaysOfCode-BE.git`
2. Setup Docker using [this link](https://docs.docker.com/get-docker/).
4. Go to root-directory using the command: <br />
   * `cd ThreeHundredDaysOfCode-BE`
5. Build the backend using the following command.<br /> 
   * `mvn clean package`
6. Deploy the backend on local using the following commands:<br />
   * `cd deployments/local` 
   * `docker-compose up`
7. Verify that the `Master Service` is up using the ping command as follows:
   * `curl localhost:80` <br />
   *  You should get `Master-Ok` as the response. 
8. Run the following command to list all the running docker containers:
   * `docker container ls` <br />
   You should see a Master Service and a Postgres server running.
9. Load the data into the database using the following command:
   * `docker exec -ti db psql -d postgres -U postgres -f  /home/data.sql` 
10. To bring down the cluster use the following commands:
    * Shutdown the cluster without deleting the data: `docker-compose down`
    * Shutdown the cluster and delete the data: `docker-compose down --volumes`

# Three Hundred Days Of Code Challenge
Here is some information around the three hundred days of code challenge.
## What is the challenge?
Your goal is to solve 1 coding problem every single day. After you solve the problem, submit the problem on ThreeHundredDaysOfCode and send a tweet with the hashtag (#300DaysOfCode).

## What are the prizes?
There are three prizes:
1. Monthly Coding Streak Challenge: Every month we will give out $200 to the person who submits a solution on the maximum number of days . You need a minimum of 20 unique days to qualify for the prize.
2. Monthly Referral Challenge: Every month we will give out $50 to the person who brings in the maximum number of people to the community. You need a minimum of 10 successful referrals to qualify for the prize.
3. The Coding God: The first person to complete a 300-day-long streak, will get a brand new IPhone. The solution should be your own and not copied from any other place.

# Contact
  * Ravi Tandon ([LinkedIn](https://www.linkedin.com/in/ravi-tandon-b6534049/), [Email](mailto:ravitandon2@gmail.com))
