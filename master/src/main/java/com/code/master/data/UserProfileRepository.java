package com.code.master.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, String>  {
    UserProfile getByUserId(String userId);
}


