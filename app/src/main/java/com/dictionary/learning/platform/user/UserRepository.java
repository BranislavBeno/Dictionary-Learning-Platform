package com.dictionary.learning.platform.user;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    @Query(
            """
                    SELECT new com.dictionary.learning.platform.user.UserDto(u.id, u.username)
                    FROM User u""")
    Set<UserDto> findAllUsers();
}
