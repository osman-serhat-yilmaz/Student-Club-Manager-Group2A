package com.app.repository;

import com.app.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    public Application findApplicationBySenderIDAndRecipientID(UUID senderId, UUID recipientId);
    public Application getById(UUID id);
}
