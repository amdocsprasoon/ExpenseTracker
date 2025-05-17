package org.sebprojects.expensetracker.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private Set<Roles> roles;

}
