package com.app.repository;

import com.app.entity.ClubRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ClubRoleRepository extends JpaRepository<ClubRole, UUID> {
    public ClubRole getById(UUID id);
    public ClubRole findClubRoleByClubIDAndUserID(UUID clubId, UUID userId);
    public List<ClubRole> findClubRolesByClubIDAndRole(UUID clubId, String role);
}
