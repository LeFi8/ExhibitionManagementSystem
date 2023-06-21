package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Client entities
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Retrieves a Client based on the email associated with its Account.
     *
     * @param email user's email
     * @return the Client entity related to the email, or null if not found
     */
    Client findByAccount_Email(String email);
}
