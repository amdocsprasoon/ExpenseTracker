package org.sebprojects.expensetracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users") // Exclude user to prevent cyclic dependency
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleId;
    String roleName;

    @ManyToMany(mappedBy = "roles") // Reference back to UserInfo
    private Set<UserInfo> users;

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
