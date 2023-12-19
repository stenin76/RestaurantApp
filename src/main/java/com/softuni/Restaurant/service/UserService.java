package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Role;
import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.model.dto.ChangeUsernameDto;
import com.softuni.Restaurant.model.dto.UserRegisterDto;
import com.softuni.Restaurant.model.enums.UserRoles;
import com.softuni.Restaurant.repository.RoleRepository;
import com.softuni.Restaurant.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(UserRegisterDto userRegisterDto) {
        UserEntity userEntityToAdd = new UserEntity();
        Role roleToAdd = this.roleRepository.findConditionByUserRoles(UserRoles.USER);

        userEntityToAdd.setUserName(userRegisterDto.getUsername());
        userEntityToAdd.setEmail(userRegisterDto.getEmail());
        userEntityToAdd.setPassword(encoder.encode(userRegisterDto.getPassword()));
        userEntityToAdd.getRoles().add(roleToAdd);

        this.userRepository.save(userEntityToAdd);
    }

    public void changeUsername(ChangeUsernameDto changeUsernameDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userToChangeName = userRepository.findByUserName(auth.getName()).get();

        userToChangeName.setUserName(changeUsernameDto.getNewUsername());
        this.userRepository.save(userToChangeName);
    }

    public void addUserRole (Long id) {
        UserEntity userToAddRole = this.userRepository.findById(id).get();

        userToAddRole.getRoles().add(roleRepository.findConditionByUserRoles(UserRoles.ADMIN));
        this.userRepository.save(userToAddRole);
    }

    public UserEntity findByUserName(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<UserEntity> getAllUsers () {
        return userRepository.findAll();
    }
}
