package com.app.repository;

import com.app.entity.ClubRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClubRoleRepository extends JpaRepository<ClubRole, UUID> {
}
