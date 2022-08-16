package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, String>  {
    UserProfile getByUserId(String userId);
    List<UserProfile> findByReferrerId(String referralId);
}


