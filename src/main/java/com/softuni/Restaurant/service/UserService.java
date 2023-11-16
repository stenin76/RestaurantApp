package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.User;
import com.softuni.Restaurant.model.dto.UserRegisterDto;
import com.softuni.Restaurant.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.softuni.Restaurant.model.enums.UserRoles.USER;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void registerUser(UserRegisterDto userRegisterDto) {
        User userToAdd = new User();

        userToAdd.setUserName(userRegisterDto.getUsername());
        userToAdd.setEmail(userRegisterDto.getEmail());
        userToAdd.setPassword(encoder.encode(userRegisterDto.getPassword()));

        this.userRepository.save(userToAdd);
    }

    public boolean checkCredentials(String username, String password) {
        User user = this.findByUserName(username);
        if (user == null) {
            return false;
        }
        return encoder.matches(password, user.getPassword());
    }
    public User findByUserName(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

}
