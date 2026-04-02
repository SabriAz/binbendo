package com.informatica.infirfs_2026.services;


import com.informatica.infirfs_2026.dao.UserRepository;
import com.informatica.infirfs_2026.models.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser customUser = userRepository.findByEmail(email);
        if (customUser == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        return new User(email,
                customUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(customUser.getRole().name())));
    }
    //Helper function for fetching user using current email
    public CustomUser getUserByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomUser customUser = userRepository.findByEmail(email);
        return customUser;
    }
}
