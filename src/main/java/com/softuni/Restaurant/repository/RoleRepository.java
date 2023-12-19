package com.softuni.Restaurant.repository;

import com.softuni.Restaurant.model.Role;
import com.softuni.Restaurant.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findConditionByUserRoles(UserRoles userRoles);
}
