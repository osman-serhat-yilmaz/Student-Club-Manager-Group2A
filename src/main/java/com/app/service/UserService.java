package com.app.service;

import com.app.repository.UserRepository;
import com.app.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOneById(UUID id) {
        return userRepository.getById(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findUsersByName(name);
    }

    public Long count() {
        return userRepository.count();
    }

}
