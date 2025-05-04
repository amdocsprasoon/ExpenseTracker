package org.sebprojects.expensetracker.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sebprojects.expensetracker.entities.Roles;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto  {

    private String username;
    private String email;
    private String password;
    private Set<Roles> roles;

}
