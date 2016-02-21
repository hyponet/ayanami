package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.user.UserAccount;

/**
 * Created by hypo on 16-2-21.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
