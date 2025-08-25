package com.mjt.Multi_Organization_Transaction_Tracker.services;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.UserEntite;
import com.mjt.Multi_Organization_Transaction_Tracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntite userEntite = repo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("email not found"));
        return User.withUsername(userEntite.getEmail()).password("{noop}").authorities("USER").build();
    }

    public UserEntite getOrCreate(String email, String name, String pictureUrl) {
        return repo.findByEmail(email).orElseGet(() -> {
            UserEntite u = UserEntite.builder().email(email).name(name).pictureUrl(pictureUrl).build();
            return repo.save(u);
        });
    }

    public UserEntite byEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("UserEntite not found"));
    }
}
