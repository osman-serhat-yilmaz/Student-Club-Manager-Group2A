package com.app.service;

import com.app.entity.User;
import com.app.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.tutorial.spring.security.formlogin.model.User;
import com.tutorial.spring.security.formlogin.repository.UserRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -< new UsernameNotFoundException("User not present"));
        return (UserDetails) user;
    }
    public void createUser(UserDetails user) {
        userRepository.save((User) user);
    }
}
