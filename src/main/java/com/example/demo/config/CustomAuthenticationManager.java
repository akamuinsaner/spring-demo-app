package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Data
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private UserDetails userDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userService.loadUserByUsername(authentication.getName());
        if (user != null) {
            if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                this.userDetails = user;
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
            } else {
                throw new BadCredentialsException("Wrong Password");
            }
        } else {
            throw new BadCredentialsException("Wrong Username");
        }
    }

}
