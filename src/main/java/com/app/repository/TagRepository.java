package com.app.repository;

import com.app.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface TagRepository extends JpaRepository<Tag, UUID> {
    public Tag getById(UUID id);
    public List<Tag> findTagByEventID(UUID eventID);
}
