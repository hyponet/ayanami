package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserAccount;

/**
 * UserAccount相关DAO
 * Created by hypo on 16-2-21.
 */
@Repository
@Transactional
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT a FROM UserAccount a WHERE a.user=:user and a.ojCode=:ojCode")
    UserAccount getAcciuntByUserAndOj(@Param("user")User user, @Param("ojCode")String ojCode);
}
