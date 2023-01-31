package com.nagarro.assignment.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(username.equals("admin"))
        {
            return User.withUsername("admin").password(bCryptPasswordEncoder.encode("admin")).roles("ADMIN").build();
        }
        if(username.equals("user"))
        {
            return User.withUsername("user").password(bCryptPasswordEncoder.encode("user")).roles("USER").build();
        }
        throw new UsernameNotFoundException("User not found with the name "+ username);
    }
}
