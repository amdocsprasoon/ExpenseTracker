package org.sebprojects.expensetracker.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto  {

    private String username;
    private String email;
    private String password;
    private Set<String> roles;

}
