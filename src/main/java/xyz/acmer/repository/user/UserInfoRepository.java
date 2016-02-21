package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.user.UserInfo;

/**
 * Created by hypo on 16-2-21.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
