package com.mas.exhibitionmanagementsystem.repositories;

import com.mas.exhibitionmanagementsystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> { }
