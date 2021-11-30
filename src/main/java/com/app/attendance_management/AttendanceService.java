package com.app.attendance_management;

import com.app.repository.AttendanceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void delete(UUID id) {
        attendanceRepository.deleteById(id);
    }

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Attendance findOneById( UUID id) {
        return attendanceRepository.getById(id);
    }

    public Attendance findAttendanceByUserIDAndEventID(UUID userId, UUID eventId) {
        return attendanceRepository.findAttendanceByUserIDAndEventID(userId, eventId);
    }

    public Long count() {
        return attendanceRepository.count();
    }
}
