package com.uis.publications.service.impl;

import com.uis.publications.exception.DataNotFoundException;
import com.uis.publications.model.User;
import com.uis.publications.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private IUserRepository userRepository;

    public User user;

    @Override
    public UserDetails loadUserByUsername(String username) {
        this.user = this.userRepository.findTopByUsername(username).orElseThrow(() -> new DataNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
