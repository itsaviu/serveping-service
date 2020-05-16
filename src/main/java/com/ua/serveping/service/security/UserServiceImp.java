package com.ua.serveping.service.security;

import com.ua.serveping.service.models.domains.Users;
import com.ua.serveping.service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Users users = userRepo.findByEmailId(emailId).orElseThrow(() -> new RuntimeException("Email Id not registered"));
        return new User(users.getEmailId(), users.getPassword(), getAuthorities(users));
    }

    private List<GrantedAuthority> getAuthorities(Users users) {
        return users.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
