package com.app.service;

import com.app.entity.ClubRole;
import com.app.repository.ClubRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class ClubRoleService {
    private final ClubRoleRepository clubRoleRepository;

    public ClubRole save(ClubRole clubRole) {
        return clubRoleRepository.save(clubRole);
    }

    public void delete(UUID id) {
        clubRoleRepository.deleteById(id);
    }

    public List<ClubRole> findAll() {
        return clubRoleRepository.findAll();
    }

    public ClubRole findOneById( UUID id) {
        return clubRoleRepository.findClubRoleById(id);
    }

    public List<ClubRole> findClubRolesByClubIDAndRole(UUID clubId, String role) {
        return clubRoleRepository.findClubRolesByClubIDAndRole(clubId,role);
    }

    public ClubRole findClubByName(UUID clubId, UUID userId) {
        return clubRoleRepository.findClubRoleByClubIDAndUserID(clubId, userId);
    }

    public Long count() {
        return clubRoleRepository.count();
    }
}
