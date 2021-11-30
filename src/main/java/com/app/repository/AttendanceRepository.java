package com.app.repository;

import com.app.attendance_management.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    public Attendance findAttendanceByUserIDAndEventID(UUID userId, UUID eventId);
    public Attendance getById(UUID id);
}