package org.sebprojects.expensetracker.repos;

import org.sebprojects.expensetracker.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserName(String userName);
    boolean existsByUserName(String userName);

}
