package com.urlShortner.urlShortner.service;

import com.urlShortner.urlShortner.models.User;
import com.urlShortner.urlShortner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService, UserDetailsPasswordService {

    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, @Nullable String newPassword) {
        return null;
    }
}
