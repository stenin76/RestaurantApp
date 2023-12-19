package com.softuni.Restaurant.service;
import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private UserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new UserDetailsService(mockUserRepo);
    }

    @Test
    void testUser_Exist() {
        UserEntity testUserEntity = createTestUser();

        when(mockUserRepo.findByUserName(testUserEntity.getUserName()))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails userDetails = toTest.loadUserByUsername(testUserEntity.getUserName());

        assertEquals(testUserEntity.getUserName(), userDetails.getUsername());
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(0, userDetails.getAuthorities().size());
    }

    @Test
    void testUserName_notExist() {

        assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non_exist"));

    }

    private static UserEntity createTestUser() {
        UserEntity testUserEntity = new UserEntity();

        testUserEntity.setUserName("admin");
        testUserEntity.setEmail("admin@admin");
        testUserEntity.setPassword("test");

        return testUserEntity;
    }

}
