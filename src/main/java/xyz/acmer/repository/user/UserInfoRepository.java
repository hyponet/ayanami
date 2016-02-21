package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserInfo;

/**
 * Created by hypo on 16-2-21.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT i FROM UserInfo i WHERE i.user = :user")
    UserInfo getUserInfoByUser(@Param("user")User user);
}
