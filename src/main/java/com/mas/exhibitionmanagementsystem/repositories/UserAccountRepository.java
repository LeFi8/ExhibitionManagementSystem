package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing user's account
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    /**
     * Retrieves user's account based on given email
     * @param email user's email
     * @return UserAccount
     */
    UserAccount findByEmail(String email);
}
