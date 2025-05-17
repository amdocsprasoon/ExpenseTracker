package org.sebprojects.expensetracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "refreshToken") // Exclude refreshToken to prevent cyclic dependency
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String userName;
    String password;

    @OneToOne(mappedBy = "userInfo") // Reference back from RefreshTokens
    private RefreshToken refreshToken;

    @ManyToMany(fetch = FetchType.EAGER) // Change fetch type to EAGER
    @JoinTable(
            name = "user_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key in join table for UserInfo
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key in join table for Roles
    )
    private Set<Roles> roles;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + userId +
                ", userName='" + userName + '\'' +
                ", password='[PROTECTED]'" + // Avoid printing sensitive data
                '}';
    }

}
