package org.sebprojects.expensetracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String userName;
    String password;

    @OneToOne(mappedBy = "userInfo") // Reference back from RefreshTokens
    private RefreshTokens refreshToken;

    @ManyToMany
    @JoinTable(
            name = "user_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key in join table for UserInfo
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key in join table for Roles
    )
    private Set<Roles> roles;

}
