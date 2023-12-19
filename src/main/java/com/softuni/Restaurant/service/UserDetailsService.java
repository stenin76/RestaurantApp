package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.repository.UserRepository;
import com.softuni.Restaurant.model.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUserName(username)
                .map(UserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }
    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getUserName())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(UserDetailsService::map).toList())
                .build();
    }
    private static GrantedAuthority map(Role userRoleEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + userRoleEntity.getUserRoles().name()
        );
    }
}
