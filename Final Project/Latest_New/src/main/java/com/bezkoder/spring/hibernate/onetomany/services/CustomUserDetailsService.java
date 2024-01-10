package com.bezkoder.spring.hibernate.onetomany.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.hibernate.onetomany.model.Admin;

import com.bezkoder.spring.hibernate.onetomany.model.Transporters;
import com.bezkoder.spring.hibernate.onetomany.model.Users;
import com.bezkoder.spring.hibernate.onetomany.repository.AdminRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.TransportersRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private TransportersRepository transportersRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // first try loading from User table
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            return new CustomUserDetailsImpl(user.getUserId(),user.getUsername(), user.getPassword(),user.getRol());

        } else {
            // Not found in user table, so check admin
        	Transporters transporter = transportersRepository.findByUsername(username);
            if (transporter != null) {
                return new CustomUserDetailsImpl(transporter.getUserId(),transporter.getUsername(), transporter.getPassword(),transporter.getRol());
            } else {
                // Not found in user table, so check admin
            	Admin admin = adminRepository.findByUsername(username);
                if (admin != null) {
                    return new CustomUserDetailsImpl(admin.getUserId(),admin.getUsername(), admin.getPassword(),admin.getRol());
                }
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
}