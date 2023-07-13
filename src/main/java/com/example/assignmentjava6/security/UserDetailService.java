package com.example.assignmentjava6.security;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.repository.AccountRepository;
import com.example.assignmentjava6.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;
    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountService.findById(username);
        String password = passwordEncoder().encode(user.getPassword());
        String[] roles = user.getAuthorities().stream()
                .map(er -> er.getRole().getId())
                .collect(Collectors.toList())
                .toArray(new String[0]);
        return User.withUsername(username).password(password).roles(roles).build();
    }
}
