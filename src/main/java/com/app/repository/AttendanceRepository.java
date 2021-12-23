package com.app.repository;

import com.app.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    public List<Attendance> findAttendancesByUserID(UUID userId);
    public List<Attendance> findAttendancesByEventID(UUID eventId);
    public Attendance getById(UUID id);

    public List<Attendance> findAttendancesByUserIDAndAttended(UUID userId, boolean attended);
}