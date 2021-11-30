package com.app.application_management;

import com.app.repository.ApplicationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public void delete(UUID id) {
        applicationRepository.deleteById(id);
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application findOneById( UUID id) {
        return applicationRepository.getById(id);
    }

    public Application findApplicationBySenderIDAndRecipientID(UUID senderId, UUID recipientId) {
        return applicationRepository.findApplicationBySenderIDAndRecipientID(senderId, recipientId);
    }

    public Long count() {
        return applicationRepository.count();
    }

}
