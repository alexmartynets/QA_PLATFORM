package com.javamentor.qa.platform.security.jwt;

import com.javamentor.qa.platform.security.service.UserDeailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserDeailsServiceImpl userRepository;

    public UserPrincipalDetailsService(UserDeailsServiceImpl userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.loadUserByUsername(s);
    }
}