package com.example.demo.security;

import com.example.demo.Model.userModel;
import com.example.demo.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
    userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {
            userModel userModel =
                    userRepository.findByUsername(username);


            if (userModel == null) {
                throw new UsernameNotFoundException(username + " does not exist");
            }
            return userModel;
        }
        }
