package org.sebprojects.expensetracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "userInfo") // Exclude refreshToken to prevent cyclic dependency
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long tokenId;
    String token;
    LocalDate expiryDate;


    @OneToOne
    @JoinColumn(name = "user_id") // Foreign key column in RefreshTokens table
    private UserInfo userInfo;

    @Override
    public String toString() {
        return "RefreshToken{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
