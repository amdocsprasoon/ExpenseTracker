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
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long roleId;
    String roleName;

    @ManyToMany(mappedBy = "roles") // Reference back to UserInfo
    private Set<UserInfo> users;

}
