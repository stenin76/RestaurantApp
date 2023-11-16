package com.softuni.Restaurant.model;

import com.softuni.Restaurant.model.enums.UserRoles;
import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    public Role() {}

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
