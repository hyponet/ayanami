package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.User;

/**
 * Created by hypo on 16-2-21.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User getUserByUsername(@Param("username")String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email")String email);
}
