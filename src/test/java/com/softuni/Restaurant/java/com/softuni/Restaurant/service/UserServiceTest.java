package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.Role;
import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.model.enums.ProductType;
import com.softuni.Restaurant.model.enums.UserRoles;
import com.softuni.Restaurant.repository.RoleRepository;
import com.softuni.Restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserService mockUserService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private RoleRepository mockRoleRepository;

    private Role testRole;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        mockUserService = new UserService(mockUserRepository, mockPasswordEncoder, mockRoleRepository);
        testRole = new Role() {
            {
                setId(1L);
                setUserRoles(UserRoles.ADMIN);
            }
        };

        testUser = new UserEntity() {
            {
                setUserName("test name");
                setEmail("test@test");
                setPassword("test");
                setRoles(new HashSet<>());
                getRoles().add(testRole);
            }
        };
    }

    @Test
    void findUserByEmail() {
        when(mockUserRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));
        mockUserService.findUserByEmail(testUser.getEmail());

        assertEquals("test@test", testUser.getEmail());
    }
    @Test
    void getAllUsers() {

        when(mockUserService.getAllUsers()).thenReturn(List.of(testUser));
        assertEquals(1,mockUserService.getAllUsers().size());
    }
}
